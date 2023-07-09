package com.cognixia.furniture_store.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognixia.furniture_store.model.User;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private List<GrantedAuthority> authorities; // permissions
    
    // when a new object is created, only RELEVANT info about our user is extracted
    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();

        // Granted Authority -> permissions/grants a user has
        //                   -> depend on what roles our user has
        this.authorities = Arrays.asList( new SimpleGrantedAuthority( user.getRole().name() ) );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // all methods after here:
    // - DON'T NEED to store this type of info in your user table
    // - store this if worthwhile for your security
    // - have all of these methods return true manually and not store this info in the table

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
        return true;
    }
    
}