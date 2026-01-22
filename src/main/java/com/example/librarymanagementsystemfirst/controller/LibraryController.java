package com.example.librarymanagementsystemfirst.controller;

import com.example.librarymanagementsystemfirst.dto.BookDTO;
import com.example.librarymanagementsystemfirst.dto.UserDTO;
import com.example.librarymanagementsystemfirst.model.*;
import com.example.librarymanagementsystemfirst.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")  // base API endPoint
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // --- Book endPoints --
    @PostMapping("/book/novel")
    public ResponseEntity<BookDTO> addNovelBook(@RequestBody NovelBook novelBook){
        Book saveBook = libraryService.addBook(novelBook);
        return ResponseEntity.ok(convertToBookDTO(saveBook));
    }
    @PostMapping("/book/textbook")
    public ResponseEntity<BookDTO> addTextBook(@RequestBody TextBook textBook){
        Book saveBook = libraryService.addBook(textBook);
        return ResponseEntity.ok(convertToBookDTO(saveBook));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getALlBooks(){
        List<BookDTO> dtos = libraryService.getAllBooks().stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/books/search")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String criteria){
        List<BookDTO> dtos = libraryService.searchBooks(criteria).stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    //-- endpoint for user
    @PostMapping("/users/member")
    public ResponseEntity<UserDTO> registerUser(@RequestBody Member member){
        User saveUser = libraryService.registerUser(member);
        return ResponseEntity.ok(convertToUserDTO(saveUser));
    }

    @PostMapping("/users/librarian")
    public ResponseEntity<UserDTO> registerUser(@RequestBody Librarian librarian){
        User saveUser = libraryService.registerUser(librarian);
        return ResponseEntity.ok(convertToUserDTO(saveUser));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> dtos = libraryService.getAllUsers().stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    // -- ending both user and book creation end points

    // -- Lending endpoints
    @PutMapping("/books/{bookId}/lend/{userId}")
    public ResponseEntity<String> lendBook(@PathVariable Long
                                           bookId, @PathVariable Long userId){
        boolean success = libraryService.lendBook(bookId, userId);
        if(success){
            return ResponseEntity.ok("Book rented Successfully");
        }else{
            return ResponseEntity.badRequest().body("Cannot Lend Book");
        }
    }
    @PutMapping("/books/{bookId}/return/{userId}")
    public ResponseEntity<String> returnBook(@PathVariable Long
                                                     bookId, @PathVariable Long userId){
        libraryService.returnBook(bookId, userId);
        return ResponseEntity.ok("Book Returned");
    }

    // --- DTO Conversion Logic ---

    private BookDTO convertToBookDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setAvailable(book.isAvailable());

        if (book instanceof NovelBook) {
            dto.setBookType("NOVEL");
            dto.setGenre(((NovelBook) book).getGenre());
        } else if (book instanceof TextBook) {
            dto.setBookType("TEXTBOOK");
            dto.setSubject(((TextBook) book).getSubject());
            dto.setEdition(((TextBook) book).getEdition());
        }
        return dto;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setContactInfo(user.getContactInfo());

        if (user instanceof Member) {
            dto.setUserType("MEMBER");
            dto.setBorrowedBooksCount(((Member) user).getBorrowedBooksCount());
        } else if (user instanceof Librarian) {
            dto.setUserType("LIBRARIAN");
            dto.setEmployeeNumber(((Librarian) user).getEmployeeNumber());
        }
        return dto;
    }
  // difference between :- RequestBody, RequestParam, PathVariable.
    // if possible :- create a frontend - 1)  display all books and users
    //                                    2) form for creating books and user.
                                   //3) Book Lend design.

}
