package com.example.librarymanagementsystemfirst;

import com.example.librarymanagementsystemfirst.model.Librarian;
import com.example.librarymanagementsystemfirst.model.Member;
import com.example.librarymanagementsystemfirst.model.NovelBook;
import com.example.librarymanagementsystemfirst.model.TextBook;
import com.example.librarymanagementsystemfirst.service.LibraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementSystemFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemFirstApplication.class, args);
    }
    // this below logic is optional, only write if you want to start your project
    // with sample data.
    @Bean
    public CommandLineRunner initialData(LibraryService libraryService) {
        return args -> {
            // create and save some Textbooks
            TextBook textbook1 = new TextBook();
            textbook1.setIsbn("ISVN1234");
            textbook1.setTitle("Java");
            textbook1.setAuthor("john doe");
            textbook1.setSubject("Java programming");
            textbook1.setEdition(3);
            libraryService.addBook(textbook1);

            // create a novel
            NovelBook novelBook1 = new NovelBook();
            novelBook1.setIsbn("ISVN1234");
            novelBook1.setTitle("The great advt");
            novelBook1.setAuthor("DAvid");
            novelBook1.setGenre("adventure");
            libraryService.addBook(novelBook1);

            // create a member
            Member m1 = new Member();
            m1.setUserName("rahul");
            m1.setContactInfo("rahul@gmail.com");
            libraryService.registerUser(m1);

            // create the librarian
            Librarian l1 = new  Librarian();
            l1.setUserName("Mohit");
            l1.setContactInfo("librarain@gmail.com");
            l1.setEmployeeNumber("EMP112");
            libraryService.registerUser(l1);

            System.out.println("--Initialized with sample data in the database");
        };
    }

}
