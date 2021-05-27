package com.exam.service;

import com.exam.model.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    int timeForAllOrders();

    void saveOrder(OrderServiceModel orderServiceModel);
    List<OrderServiceModel> findAllOrdersOrdered();

    void deleteById(Long id);
}
