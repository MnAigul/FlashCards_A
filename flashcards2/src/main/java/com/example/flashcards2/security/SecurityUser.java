package com.example.flashcards2.security;

import com.example.flashcards2.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getRole();
       // List<Role>  roles = user.getRoles();
        System.out.println("GrantedAuthority = " + user.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        /*
        for (Role role : roles) {
            System.out.println("ROLES = " + role);
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

         */



        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return  true;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), true, true, true, true,
                new SecurityUser(user).getAuthorities());
    }
}
