package com.hcl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Table
@Entity(name = "product")
public class Product {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column (name="prdId")
	private Long id;
	@Column (name="prdName")
	private String name;
	@Column (name="prdBrand")
	private String brand;
	@Column (name="prdPrice")
	private Double price;
	@Column(name="qty")
	private int quantity;
	@Column (name= "total")
	private Double total;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", total="
				+ total + "]";
	}
	

	
	
	
	
}
