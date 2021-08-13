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
public class OrderDetailDTO implements Serializable{
    
    private int orderDetailID;
    private int bookID;
    private int quantity;
    private float unitPrice;
    private float total;
    private int orderID;
    private String title;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailID, int bookID, int quantity, float unitPrice, float total, int orderID, String title) {
        this.orderDetailID = orderDetailID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.orderID = orderID;
        this.title = title;
    }

    public OrderDetailDTO(int quantity, float unitPrice, float total, String title) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.title = title;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}
