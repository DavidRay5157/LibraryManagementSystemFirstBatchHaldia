package com.example.librarymanagementsystemfirst.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // this will create table in DB
@Table(name = "users")  // postgres have inbuit datatype of User, so when creating User class
// rename the table name to users
@Getter  // this are lombok annotation for getter and setter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // for inheritance
@DiscriminatorColumn(name = "user_type",  discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String contactInfo;

    public abstract boolean canBorrowBooks();
}
