package com.example.flashcards2.controller;


import com.example.flashcards2.entity.Folder;
import com.example.flashcards2.entity.User;
import com.example.flashcards2.entity.Word;
import com.example.flashcards2.repository.FolderRepository;
import com.example.flashcards2.repository.UserRepository;
import com.example.flashcards2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class FolderController {


    private Folder folderFromDB;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;



    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("folder", new Folder());
        return "add";
    }

    @GetMapping("/showWords/{folderId}")
    public String showWords(@PathVariable("folderId") int folderId, Model model) {
        this.folderFromDB = folderRepository.getById(folderId);
        System.out.println(this.folderFromDB);
        List<Word> allWords = folderFromDB.getWords();
        model.addAttribute("words", allWords);
        return "words";
    }

    @PostMapping("/saveFolder")
    public String add(@ModelAttribute("folder") Folder folder, Model model) {
        System.out.println("yatut");
        User userFromDB = null;
        userFromDB = userController.getUserFromDB();
        userFromDB = userRepository.getReferenceById(userFromDB.getId());
        userFromDB.addFoldertoUser(folder);
        userRepository.save(userFromDB);
        int id = userFromDB.getId();
        userFromDB = userRepository.getReferenceById(id);
        userController.setUserFromDB(userFromDB);
        List<Folder> allFolders = userFromDB.getFolders();
        System.out.println("Save folder " + allFolders);
        model.addAttribute("folders", allFolders);
        return "redirect:/account";
    }


    @GetMapping("/delete/{folderId}")
    public String deleteFolder(@PathVariable("folderId") int folderId, Model model) {
        User userFromDB = null;
        userFromDB = userController.getUserFromDB();
        userFromDB = userRepository.getReferenceById(userFromDB.getId());
        userFromDB.deleteFolderOfUser(folderRepository.getReferenceById(folderId));
        userRepository.save(userFromDB);
        userFromDB = userRepository.getReferenceById(userFromDB.getId());
        userController.setUserFromDB(userFromDB);
        List<Folder> allFolders = userFromDB.getFolders();
        System.out.println("Delete folder " + allFolders);
        model.addAttribute("folders", allFolders);
        return "redirect:/account";
    }

    public Folder getFolderFromDB() {
        return this.folderFromDB;
    }

    public void setFolderFromDB(Folder folderFromDB) {
        this.folderFromDB = folderFromDB;
    }

}
