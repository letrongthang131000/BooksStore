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
public class OrderDTO implements Serializable{
    
    private int orderID;
    private String orderDate;
    private String orderDay;
    private String bookID;
    private String title;
    private int quantity;
    private float unitPrice;
    private float total;
    private float totalOrder;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, String orderDate, String orderDay, String bookID, String title, int quantity, float unitPrice, float total, float totalOrder) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderDay = orderDay;
        this.bookID = bookID;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.totalOrder = totalOrder;
    }

    public OrderDTO(int orderID, String orderDate, String orderDay, String bookID, String title, int quantity, float unitPrice, float total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderDay = orderDay;
        this.bookID = bookID;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public OrderDTO(int orderID, String orderDate, String bookID, String title, int quantity, float unitPrice, float total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.bookID = bookID;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public float getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(float totalOrder) {
        this.totalOrder = totalOrder;
    }

    
    
    
    
}
