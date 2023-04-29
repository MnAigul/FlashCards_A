package com.example.flashcards2.service;

import com.example.flashcards2.entity.Folder;
import com.example.flashcards2.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FolderServiceImpl implements FolderService{

    @Autowired
    private FolderRepository folderRepository;

    @Override
    public void saveFolder(Folder folder) {
        folderRepository.save(folder);
    }
}
