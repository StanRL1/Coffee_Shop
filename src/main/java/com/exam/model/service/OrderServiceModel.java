package com.exam.model.service;

import com.exam.model.entity.enums.CategoryName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel implements Comparable<OrderServiceModel> {
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    @Size(min=3,max=20,message = "Name has to be between 3 and 20")
    private String name;
    @NotNull(message = "Price cannot be blank")
    @DecimalMin(value = "0",message = "Price has to be a positive number")
    private BigDecimal price;
    @PastOrPresent(message = "Date cannot be in the future")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;
    @NotNull(message = "Category cannot be blank")
    private CategoryName category;
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5,message = "Description has to be longer than 5")
    private String description;
    @NotNull
    private String username;

    public OrderServiceModel() {
    }

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

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public int compareTo(OrderServiceModel u) {
        if (getPrice() == null || u.getPrice() == null) {
            return 0;
        }

        if(getPrice().compareTo(u.getPrice())==1){
            return -1;
        }else if(getPrice().compareTo(u.getPrice())==-1){
            return 1;
        }

        return 0;
    }
}
