package com.example.flashcards2.entity;




import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "foldername")
    private String folder_name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private List<Word> words;

    public Folder() {
    }

    public Folder( String folder_name) {
        this.folder_name = folder_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void addWordtoFolder(Word word) {
        if (words == null) {
            words = new ArrayList<Word>();
        }
        words.add(word);
    }

    public void deleteWordOfFolder(Word word) {
        this.words.remove(word);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folder_id=" + id +
                ", folder_name='" + folder_name + '\'' +
                '}';
    }
}
