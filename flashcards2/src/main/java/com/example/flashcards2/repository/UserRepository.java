package com.example.flashcards2.repository;



import com.example.flashcards2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("delete from User u")
    void deleteFirstBy();
    Optional<User> getByemail(String email);

    Optional<User> findByName(String name);

    User getReferenceById(int id);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByUsername(@Param("email") String email);

    User findByEmail(String email);
}
