package com.lanapavlova.controller;

import com.lanapavlova.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/add-user")
    public String showAddUserForm() {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam String name,
                          @RequestParam int age,
                          RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(name, age);
            return "redirect:/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding user: " + e.getMessage());
            return "redirect:/add-user";
        }
    }

    @GetMapping("/edit-user")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam int age) {
        userService.updateUser(id, name, age);
        return "redirect:/users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}