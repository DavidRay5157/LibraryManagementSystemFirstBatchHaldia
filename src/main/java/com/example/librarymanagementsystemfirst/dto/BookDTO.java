package com.example.librarymanagementsystemfirst.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private boolean available;
    private String bookType; // discriminator value
    private String genre; // this is for Novels
    private String subject; // for subject
    private String edition;
}
