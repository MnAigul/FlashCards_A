package com.example.flashcards2.controller;



import com.example.flashcards2.entity.Folder;
import com.example.flashcards2.entity.Word;
import com.example.flashcards2.repository.FolderRepository;
import com.example.flashcards2.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class WordController {


    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private FolderController folderController;


    @GetMapping("/addWord")
    public String addWord(Model model) {
        model.addAttribute("word", new Word());
        return "add-word";
    }

    @PostMapping("/saveWord")
    public String addWord(@ModelAttribute("word") Word word, Model model) {
        Folder folderFromDB = folderController.getFolderFromDB();
        folderFromDB = folderRepository.getReferenceById(folderFromDB.getId());
        System.out.println(folderFromDB.getWords());
        folderFromDB.addWordtoFolder(word);
        folderRepository.saveAndFlush(folderFromDB);
        folderFromDB = folderRepository.getReferenceById(folderFromDB.getId());
        folderController.setFolderFromDB(folderFromDB);
        System.out.println(folderFromDB.getWords());
        model.addAttribute("words", folderFromDB.getWords());
        return "redirect:/account/showWords";
    }

    @GetMapping("/showWords")
    public String showWords(Model model) {
        Folder folderFromDB = folderController.getFolderFromDB();
        folderFromDB = folderRepository.getReferenceById(folderFromDB.getId());
        List<Word> allWords = folderFromDB.getWords();
        model.addAttribute("words", allWords);
        return "words";
    }

    @GetMapping("/deleteWord/{wordId}")
    public String deleteWord(@PathVariable("wordId") int wordId, Model model) {
        Folder folderFromDB = folderController.getFolderFromDB();
        folderFromDB = folderRepository.getReferenceById(folderFromDB.getId());
        folderFromDB.deleteWordOfFolder(wordRepository.getReferenceById(wordId));
        folderRepository.save(folderFromDB);
        folderFromDB = folderRepository.getReferenceById(folderFromDB.getId());
        folderController.setFolderFromDB(folderFromDB);
        List<Word> allWords = folderFromDB.getWords();
        model.addAttribute("words", allWords);
        return "redirect:/account/showWords";
    }

}
