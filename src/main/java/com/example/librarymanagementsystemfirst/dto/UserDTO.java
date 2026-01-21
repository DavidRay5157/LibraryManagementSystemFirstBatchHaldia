package com.example.librarymanagementsystemfirst.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String contactInfo;
    private String userType;
    private Integer borrowedBooksCount;
    private String employeeNumber;
}
