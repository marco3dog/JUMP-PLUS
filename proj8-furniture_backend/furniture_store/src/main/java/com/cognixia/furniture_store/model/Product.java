package com.cognixia.furniture_store.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(nullable = false)
    @Schema(description="Item Name")
    @NotBlank
    private String name;

    @Column(nullable = false)
    @Schema(description="Image URL")
    @NotBlank
    private String url;

    @Min(value=0)
    @Schema(description="cost")
	private Double cost;

    @Min(value=0)
	@Schema(description="number in stock")
	private Integer amount;

    public Product(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", cost='" + getCost() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }
    

}
