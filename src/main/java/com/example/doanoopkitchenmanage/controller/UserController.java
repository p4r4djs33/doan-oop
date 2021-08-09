package com.example.doanoopkitchenmanage.controller;

import com.example.doanoopkitchenmanage.model.Login;
import com.example.doanoopkitchenmanage.model.User;
/*import com.example.doanoopkitchenmanage.model.UserDao;*/
import com.example.doanoopkitchenmanage.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login/login", "login", new Login());
        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView home(@ModelAttribute("login") Login login){
        List<User> users = userService.findAll();
        User user = new User();
        for(User u:users) {
            if (u.getAccount().equals(login.getAccount())
            && u.getPassword().equals(login.getPassword())) {
                user = u;
                break;
            }
            user=null;
        }
        ModelAndView modelAndView;
        if(user == null){
            modelAndView = new ModelAndView("error");
        } else {
            modelAndView = new ModelAndView("login/home","user",user);
/*            modelAndView.addObject("user", user);*/
        }
        return modelAndView;
    }
    @GetMapping("/home")
    public String home() {
        return "login/home";
    }
    @GetMapping("/home/dish")
    public String index(Model model) {
        return "dish/homeDish";
    }
}
