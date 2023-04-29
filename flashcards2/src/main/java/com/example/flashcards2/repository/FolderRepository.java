package com.example.flashcards2.repository;

import com.example.flashcards2.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Integer> {
    Folder getReferenceById(int id);

    Folder getById(int folderId);
}
