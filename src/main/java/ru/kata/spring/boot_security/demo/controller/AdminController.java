package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, UserDetailsService userDetailsService, RoleService roleService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String index(Principal principal, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("myUser", userDetailsService.loadUserByUsername(principal.getName()));
        return "admin/index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("myUser", userDetailsService.loadUserByUsername(principal.getName()));
        return "admin/add";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add";
        }
        userService.setUserRoles(user);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model,Principal principal) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", userService.getAllUsers());
        model.addAttribute("userPage", userDetailsService.loadUserByUsername(principal.getName()));
        return "admin/edit";
    }

    @PatchMapping("/")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        userService.setUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
