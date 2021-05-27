package com.exam.service.impl;

import com.exam.model.entity.Category;
import com.exam.model.entity.Order;
import com.exam.model.entity.User;
import com.exam.model.service.OrderServiceModel;
import com.exam.repository.OrderRepository;
import com.exam.service.CategoryService;
import com.exam.service.OrderService;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public int timeForAllOrders() {
        if(this.orderRepository.findAll().size()==0){
            return 0;
        }

        return this.orderRepository.findTimeForAllOrders();
    }

    @Override
    public void saveOrder(OrderServiceModel orderServiceModel) {
        User user=this.userService.findByUsername(orderServiceModel.getUsername());
        Category category= this.categoryService.findByCategoryName(orderServiceModel.getCategory());

        Order order=this.modelMapper.map(orderServiceModel,Order.class);
        order.setCategory(category);
        order.setEmployee(user);
        user.getOrders().add(order);
        System.out.println();
        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public List<OrderServiceModel> findAllOrdersOrdered() {

        List<OrderServiceModel> orderServiceModels= this.orderRepository.findAll().stream().
                map(order->{
                    OrderServiceModel orderServiceModel=this.modelMapper.map(order,OrderServiceModel.class);
                    orderServiceModel.setCategory(order.getCategory().getName());
                    orderServiceModel.setUsername(order.getEmployee().getUsername());
                    return orderServiceModel;
                }).collect(Collectors.toList());

        Collections.sort(orderServiceModels);

        return orderServiceModels;
    }

    @Override
    public void deleteById(Long id) {
        this.orderRepository.deleteById(id);
    }


}
