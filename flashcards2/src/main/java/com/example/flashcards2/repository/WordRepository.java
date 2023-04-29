package com.example.flashcards2.repository;


import com.example.flashcards2.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
    Word getReferenceById(int wordId);
}
