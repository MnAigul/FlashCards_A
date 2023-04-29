package com.example.flashcards2.service;

import com.example.flashcards2.entity.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    Optional<User> findByUsername(String name);

    User emailAlreadyExists(String email);

//    void addFoldertoUser(Folder folder);
/*
    public boolean correctUserData(User user);

 */
}
