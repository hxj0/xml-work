package com.hxj.book.entity;

import java.util.Date;

public class Book {
	private	Integer id;
	private String name;
	private String author;
	private String publishDate;
	private Double price;
	private String description;
	private String coverUrl;
	
	
	
	public Book(Integer id, String name, String author, String publishDate, Double price, String description,
			String coverUrl) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.publishDate = publishDate;
		this.price = price;
		this.description = description;
		this.coverUrl = coverUrl;
	}
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
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description; 
	}
	

	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", publishDate=" + publishDate + ", price="
				+ price + ", description=" + description + ", coverUrl=" + coverUrl + "]";
	}
	
}
