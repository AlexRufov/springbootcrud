package com.rufovav.springbootcrud.controller;

import com.rufovav.springbootcrud.model.User;
import com.rufovav.springbootcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> all = userService.findAll();
        model.addAttribute("users", all);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable(value = "id") long id, Model model){
        User byId = userService.findById(id);
        model.addAttribute("user", byId);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(Model model, User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
}
