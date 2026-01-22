package com.example.librarymanagementsystemfirst.controller;

import com.example.librarymanagementsystemfirst.dto.BookDTO;
import com.example.librarymanagementsystemfirst.model.Book;
import com.example.librarymanagementsystemfirst.model.NovelBook;
import com.example.librarymanagementsystemfirst.model.TextBook;
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

    private BookDTO convertToBookDTO(Book saveBook) {
    }


}
