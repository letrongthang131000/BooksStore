/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.dtos;

import java.io.Serializable;

/**
 *
 * @author Thang
 */
public class BookDTO implements Serializable{
    
    private int bookID;
    private String title;
    private float price;
    private String image;
    private String author;
    private String description;
    private int quantity;
    private String createDate;
    private String status;
    private String categoryName;

    public BookDTO() {
    }

    
    public BookDTO(int bookID, String title, float price, String image, String author, String description, int quantity, String createDate, String status, String categoryName) {
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.image = image;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
        this.createDate = createDate;
        this.status = status;
        this.categoryName = categoryName;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
    
}
