package com.example.flashcards2.repository;


import com.example.flashcards2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getReferenceById(long l);


}
