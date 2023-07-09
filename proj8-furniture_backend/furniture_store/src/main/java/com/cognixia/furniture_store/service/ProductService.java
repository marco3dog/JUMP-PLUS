package com.cognixia.furniture_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.furniture_store.exception.OutOfStockException;
import com.cognixia.furniture_store.exception.ResourceNotFoundException;
import com.cognixia.furniture_store.model.Product;
import com.cognixia.furniture_store.repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    ProductRepo repo;

    public Product createProduct(Product product){
        product.setId(null);
        Product created = repo.save(product);
        return created;
    }

    public List<Product> getProducts(){
        return repo.findAll();
    }

    public Product getProductById(int id) throws ResourceNotFoundException {
		
		Optional<Product> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("No such product exists");
		}
		
		return found.get();
	}

    public boolean purchaseProductById(int id) throws ResourceNotFoundException, OutOfStockException{

        Product purchased = getProductById(id);
        Integer amount = purchased.getAmount();

        if(amount >= 1){
            purchased.setAmount(--amount);
            repo.save(purchased);
            return true;      
        }

        throw new OutOfStockException("Item out of stock");
    }

    public Product updateProduct(Product product) throws ResourceNotFoundException {
		
		boolean exists = repo.existsById(product.getId());
		
		if(exists) {
		
			Product updated = repo.save(product);
			
			return updated;
			
		}
		
		throw new ResourceNotFoundException( "No such user exists" );
	}
}
