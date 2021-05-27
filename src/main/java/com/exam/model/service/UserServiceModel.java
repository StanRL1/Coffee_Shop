package com.exam.model.service;

import com.exam.model.entity.Order;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserServiceModel implements Comparable<UserServiceModel> {
    @NotBlank()
    @Size(min=3,max=20)
    private String username;
    @Email()
    private String email;
    @NotBlank()
    @Size(min=3,max=20)
    private String password;
    @NotBlank()
    private String firstName;
    @NotBlank()
    @Size(min=5,max = 20)
    private String lastName;
    private List<Order> orders;

    public UserServiceModel() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(UserServiceModel u) {
        if(getOrders().size()<u.getOrders().size()){
            return 1;
        }else if (getOrders().size()==u.getOrders().size()){
            return 0;
        }

        return -1;
    }
}
