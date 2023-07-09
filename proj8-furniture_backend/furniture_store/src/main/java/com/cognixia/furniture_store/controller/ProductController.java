package com.cognixia.furniture_store.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.furniture_store.exception.OutOfStockException;
import com.cognixia.furniture_store.exception.ResourceNotFoundException;
import com.cognixia.furniture_store.model.Product;
import com.cognixia.furniture_store.service.ProductService;



@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/product")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) throws ResourceNotFoundException {
		
		Product found = service.getProductById(id);
		
		return ResponseEntity.status(200).body( found );
		 
	}

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product){
        product.setId(null);
        Product created = service.createProduct(product);

        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product) throws ResourceNotFoundException{
        Product updated = service.updateProduct(product);

        return ResponseEntity.status(200).body(updated);
    }

    @PutMapping("product/purchase/{id}")
    public ResponseEntity<?> purchaseById(@PathVariable int id) throws ResourceNotFoundException, OutOfStockException{

        Product purchased = service.getProductById(id);
        service.purchaseProductById(id);
        return ResponseEntity.status(200).body(purchased);
    }
    
}
