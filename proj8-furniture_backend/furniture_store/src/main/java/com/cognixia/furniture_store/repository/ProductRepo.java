package com.cognixia.furniture_store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.furniture_store.model.Product;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
    
}
