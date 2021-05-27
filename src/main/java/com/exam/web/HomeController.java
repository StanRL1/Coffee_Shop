package com.exam.web;

import com.exam.model.view.OrderViewModel;
import com.exam.model.view.UserViewModel;
import com.exam.service.OrderService;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public HomeController(OrderService orderService, ModelMapper modelMapper, UserService userService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("user")==null) {
            return "index";
        }
        List<UserViewModel> userViewModelList=this.userService.findAllSortedByOrders().stream().
                map(user->{
                    UserViewModel userViewModel=this.modelMapper.map(user,UserViewModel.class);
                    userViewModel.setNumberOfOrders(user.getOrders().size());
                    return userViewModel;
                }).collect(Collectors.toList());
        List<OrderViewModel> orderViewModels=this.orderService.findAllOrdersOrdered().stream().
                map(order->{
                    OrderViewModel orderViewModel=this.modelMapper.map(order,OrderViewModel.class);
                    orderViewModel.setCategory(orderViewModel.getCategory().toLowerCase());
                    return orderViewModel;
                }).collect(Collectors.toList());

        model.addAttribute("employees",userViewModelList);
        model.addAttribute("orders",orderViewModels);
        model.addAttribute("timeForOrders",this.orderService.timeForAllOrders());

         return "home";
        }
}
