package com.example.flashcards2.service;



import com.example.flashcards2.security.SecurityUser;
import com.example.flashcards2.entity.User;
import com.example.flashcards2.repository.RoleRepository;
import com.example.flashcards2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;





    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");
        //Set<Role> roles = new HashSet<Role>();
        //roles.add(roleRepository.getReferenceById(1L));
        //user.setRole(roleRepository.getReferenceById(1L));
       userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByName(name);

    }


    @Override
    public User  emailAlreadyExists(String email) {
        boolean f = false;
        User user = null;
        System.out.println(user);
        Optional<User> usr = userRepository.getByemail(email);
        System.out.println(usr);
        if (usr.isPresent()) {
            f = true;
            user = usr.get();
        }
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println("user = " + user);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return SecurityUser.fromUser(user);
    }
}
