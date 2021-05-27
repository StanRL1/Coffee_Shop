package com.exam.model.entity;

import org.attoparser.dom.Text;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order extends BaseEntity{
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="price",nullable = false)
    private BigDecimal price;
    @Column(name="order_time",nullable = false)
    private LocalDateTime orderTime;
    @OneToOne
    private Category category;
    @Column(name="description",nullable = false,length=512)
    private String description;
    @ManyToOne
    private User employee;

    public Order() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }
}
