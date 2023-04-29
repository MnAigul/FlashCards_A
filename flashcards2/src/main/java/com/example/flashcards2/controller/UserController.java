package com.example.flashcards2.controller;


import com.example.flashcards2.entity.Folder;
import com.example.flashcards2.entity.Role;
import com.example.flashcards2.entity.User;
import com.example.flashcards2.repository.RoleRepository;
import com.example.flashcards2.repository.UserRepository;
import com.example.flashcards2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
/*
    @Autowired
    private RoleRepository roleRepository;

 */
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private SecurityService securityService;

    private User userFromDB;


    @GetMapping("/")
    public String HomePage(Model model) {
        model.addAttribute("user", new User());
        System.out.println("yatut");
        return "home-page";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup_finish")
    public String processRegister(@ModelAttribute("user")  User user, Model model) {
        User userFromDB = userService.emailAlreadyExists(user.getEmail());
        if (userFromDB == null) {
           // Set<Role> roles = new HashSet<>();
            //roles.add(roleRepository.getReferenceById(1L));
            //user.setRole(roleRepository.getReferenceById(1L));

            userService.saveUser(user);
            //securityService.autoLogin(user.getName(), user.getPassword());
        } else {
            model.addAttribute("userexistsemailError", "An account with this email already exists");
            return "signup";
        }
        return "register-success";
    }


    @PostMapping(value = "/login-success")
    public String login(@ModelAttribute("user") User user, Model model) {
        System.out.println(user);

        this.userFromDB = null;
        this.userFromDB = userService.emailAlreadyExists(user.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(this.userFromDB == null) {
            model.addAttribute("useremailError", "There is no account with this email");
            return "home-page";
        } else {
            if (!encoder.matches(user.getPassword(), this.userFromDB.getPassword())) {
                model.addAttribute("userpassError", "Wrong password!");
                return "home-page";
            } else {
                List<Folder> allFolders = this.userFromDB.getFolders();
                model.addAttribute("folders", allFolders);
            }
        }
        return "account";
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/account")
    public String account(Model model) {
        List<Folder> allFolders = this.userFromDB.getFolders();
        System.out.println(allFolders);
        model.addAttribute("folders", allFolders);
        return "account";
    }






    public  User getUserFromDB() {
        return this.userFromDB;
    }

    public  void setUserFromDB(User userFromDB) {
        this.userFromDB = userFromDB;
    }
}
