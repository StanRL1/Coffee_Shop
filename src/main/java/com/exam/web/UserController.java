package com.exam.web;

import com.exam.model.binding.UserLoginBindingModel;
import com.exam.model.binding.UserRegistrationBindingModel;
import com.exam.model.service.UserServiceModel;
import com.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("userRegistrationBindingModel")) {
            model.addAttribute("userRegistrationBindingModel",new UserRegistrationBindingModel() );
            model.addAttribute("isExists",false);

        }
        return "/register";}
        //th:object="${obekta}"
        //th:if="${#fields.hasErrors("name of the field")}" th:errors="*{ime na field}"
        //th:each="e:${#fields.errors()}" th:object="${e}"
        //<small th:text="${e}"><br/>
        //th:each="c: ${T(path.to.the.enum).values()}" th:text="${c}" th:value="${c}"
    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }
        return "/login";}

    @PostMapping("/register")
    public String registerPost(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println();
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);

            return "redirect:/users/register";
        }

            boolean isSaved=this.userService.register(this.modelMapper.map(userRegistrationBindingModel,UserServiceModel.class));

            if(!isSaved){
                redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
                redirectAttributes.addFlashAttribute("isExists", true);
                return "redirect:/users/register";
            }
        System.out.println();
        return "redirect:/users/login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid UserLoginBindingModel userLoginBindingModel,BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,HttpSession session){

        UserServiceModel userServiceModel=this.userService.findByUsernameAndPassword(userLoginBindingModel.getPassword(),userLoginBindingModel.getUsername());
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:/users/login";

        }

        if(userServiceModel==null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound",true);
            return "redirect:/users/login";

        }

        session.setAttribute("user",userServiceModel);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/";
        }
        session.invalidate();


        return "redirect:/";
    }

}
