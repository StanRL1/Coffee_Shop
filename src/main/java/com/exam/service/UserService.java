package com.exam.service;

import com.exam.model.entity.User;
import com.exam.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel findByUsernameAndPassword(String password, String username);

    boolean register(UserServiceModel userServiceModel);

    User findByUsername(String username);

    List<UserServiceModel> findAllSortedByOrders();

    void deleteOrderById(Long id);
}
