package com.exam.service.impl;

import com.exam.model.entity.User;
import com.exam.model.service.UserServiceModel;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String password, String username) {
        return this.userRepository.findByUsernameAndPassword(username,password).map(user->this.modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }

    @Override
    public boolean register(UserServiceModel userServiceModel) {
        try{
            System.out.println();
            userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
        }catch (Exception e){
            return false;
        }

        return true ;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<UserServiceModel> findAllSortedByOrders() {
        List<UserServiceModel> userServiceModels=this.userRepository.findAll().stream().
                map(user -> this.modelMapper.map(user,UserServiceModel.class)).collect(Collectors.toList());

        Collections.sort(userServiceModels);

        return userServiceModels;
    }

    @Override
    public void deleteOrderById(Long id) {
        List<User> users=this.userRepository.findAll();

        users.forEach(user-> {
            user.getOrders().forEach(order -> {
                if(order.getId().equals(id)){
                    user.getOrders().remove(id);
                }
            });
        });


    }
}
