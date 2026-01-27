package com.example.librarymanagementsystemfirst.repository;

import com.example.librarymanagementsystemfirst.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
