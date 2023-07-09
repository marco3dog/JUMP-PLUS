package com.cognixia.furniture_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.furniture_store.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    
}