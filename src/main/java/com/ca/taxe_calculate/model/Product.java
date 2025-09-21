package com.ca.taxe_calculate.model;

import com.ca.taxe_calculate.enumeration.Category;

/**
 * Classe représentant un produit dans le panier.
 */
public class Product {

	private String name;
	private double price;
	private int quantity;
	private boolean imported;
	private Category category;

	public Product(String name, double price, int quantity, boolean imported, Category category) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.imported = imported;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
