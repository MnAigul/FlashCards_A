package com.example.flashcards2.service;

import com.example.flashcards2.entity.Word;
import com.example.flashcards2.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WordServiceImpl implements WordService{

    @Autowired
    private WordRepository wordRepository;

    @Override
    public void saveWord(Word word) {
        wordRepository.save(word);
    }
}
