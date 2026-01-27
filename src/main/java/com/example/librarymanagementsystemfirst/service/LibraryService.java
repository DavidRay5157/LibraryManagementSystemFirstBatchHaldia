package com.example.librarymanagementsystemfirst.service;

import com.example.librarymanagementsystemfirst.model.Book;
import com.example.librarymanagementsystemfirst.model.Member;
import com.example.librarymanagementsystemfirst.model.User;
import com.example.librarymanagementsystemfirst.repository.BookRepository;
import com.example.librarymanagementsystemfirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public User registerUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public List<Book> searchBooks(String criteria){
        return bookRepository.findByAuthorContainingIgnoreCaseOrAuthorContainingIgnoreCase(criteria, criteria);
    }
    @Transactional
    public boolean lendBook(Long bookId, Long userId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book is Not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        if(book.isAvailable() && user.canBorrowBooks()){
            book.setAvailable(false);
            if(user instanceof Member){
                Member member  = (Member)user;
                member.setBorrowedBooksCount(member.getBorrowedBooksCount() + 1);
                userRepository.save(member);
            }
            bookRepository.save(book);
            return true;
        }
        return false;
    }
    @Transactional
    public void returnBook(Long bookId, Long userId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book is Not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        if(!book.isAvailable()){
            book.setAvailable(true);
            if(user instanceof Member){
            Member member  = (Member)user;
            member.setBorrowedBooksCount(member.getBorrowedBooksCount() - 1);
            userRepository.save(member);
            }
            bookRepository.save(book);
        }
    }
















}
