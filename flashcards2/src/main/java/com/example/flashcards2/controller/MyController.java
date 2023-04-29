package com.example.flashcards2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
/*
    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    private User userFromDB;
    private Folder folderFromDB;

    @Autowired
    private FolderRepository folderRepository;


    @GetMapping("/")
    public String HomePage(Model model) {
        model.addAttribute("user", new User());
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
            userService.saveUser(user);
        } else {
            model.addAttribute("userexistsemailError", "An account with this email already exists");
            return "signup";
        }
        return "register-success";
    }

    @PostMapping("/")
    public String login(@ModelAttribute("user") User user, Model model) {
        this.userFromDB = null;
        this.userFromDB = userService.emailAlreadyExists(user.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String pass = encoder.encode(user.getPassword());
        //user.setPassword(pass);
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

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("folder", new Folder());
        return "add";
    }

    @GetMapping("/addWord")
    public String addWord(Model model) {
        model.addAttribute("word", new Word());
        return "add-word";
    }

    @PostMapping("/saveFolder")
    public String add(@ModelAttribute("folder") Folder folder, Model model) {
        this.userFromDB.addFoldertoUser(folder);
        userRepository.save(this.userFromDB);
        int id = this.userFromDB.getId();
        this.userFromDB = userRepository.getReferenceById(id);
        model.addAttribute("folders", this.userFromDB.getFolders());
        return "account";
    }
    @PostMapping("/saveWord")
    public String addWord(@ModelAttribute("word") Word word, Model model) {
        this.folderFromDB.addWordtoFolder(word);
        folderRepository.saveAndFlush(this.folderFromDB);
        model.addAttribute("words", this.folderFromDB.getWords());
        return "words";
    }

    @GetMapping("/showWords/{folderId}")
    public String showWords(@PathVariable("folderId") int folderId, Model model) {
        this.folderFromDB = folderRepository.getById(folderId);
        System.out.println(this.folderFromDB);
        List<Word> allWords = folderFromDB.getWords();
        model.addAttribute("words", allWords);
        return "words";
    }

    @GetMapping("/delete/{folderId}")
    public String deleteFolder(@PathVariable("folderId") int folderId, Model model) {
        Folder folder = folderRepository.getById(folderId);
        folderRepository.deleteById(folderId);
        int id = this.userFromDB.getId();
        this.userFromDB = userRepository.getReferenceById(id);

        List<Folder> allFolders = this.userFromDB.getFolders();
        model.addAttribute("folders", allFolders);
        return "account";
    }
    @GetMapping("/account")
    public String account(Model model) {
        List<Folder> allFolders = this.userFromDB.getFolders();
        System.out.println(allFolders);
        model.addAttribute("folders", allFolders);
        return "account";
    }
    */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

/*
    public Folder getFolderFromDB() {
        return folderFromDB;
    }

    public void setFolderFromDB(Folder folderFromDB) {
        this.folderFromDB = folderFromDB;
    }

 */
}
