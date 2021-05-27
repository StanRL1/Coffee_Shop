package com.exam.web;

import com.exam.model.binding.OrderAddBindingModel;
import com.exam.model.service.OrderServiceModel;
import com.exam.model.service.UserServiceModel;
import com.exam.service.OrderService;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OrderController(OrderService orderService, ModelMapper modelMapper, UserService userService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/add")
    public String add(Model model,HttpSession httpSession){
        if(httpSession.getAttribute("user")==null){
            return "redirect:/";
        }
        if(!model.containsAttribute("orderAddBindingModel")){
            model.addAttribute("orderAddBindingModel",new OrderAddBindingModel());
        }
        return "order-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid OrderAddBindingModel orderAddBindingModel, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, HttpSession httpSession){
        if(httpSession.getAttribute("user")==null){
            return "redirect:/users/login";
        }

        UserServiceModel userServiceModel=this.modelMapper.map(httpSession.getAttribute("user"),UserServiceModel.class);
        orderAddBindingModel.setUsername(userServiceModel.getUsername());

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);
            return "redirect:add";
        }
        System.out.println();
        this.orderService.saveOrder(this.modelMapper.map(orderAddBindingModel, OrderServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id")Long id){
        this.orderService.deleteById(id);
        this.userService.deleteOrderById(id);
        return "redirect:/";
    }



}
