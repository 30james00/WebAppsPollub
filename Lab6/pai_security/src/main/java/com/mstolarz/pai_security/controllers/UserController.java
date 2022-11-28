package com.mstolarz.pai_security.controllers;

import com.mstolarz.pai_security.dao.UserDao;
import com.mstolarz.pai_security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;

    @GetMapping("/login")
    public String loginPage() {
        //zwrócenie nazwy widoku logowania - login.html
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        //dodanie do modelu nowego użytkownika
        m.addAttribute("user", new User());
        //zwrócenie nazwy widoku rejestracji - register.html
        return "register";
    }

    @GetMapping("/edit")
    public String editPage(Model m, Principal principal) {
        //dodanie do modelu nowego użytkownika
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "edit";
    }

    @GetMapping("/delete")
    public String deletePage(Principal principal) {
        dao.delete(dao.findByLogin(principal.getName()));
        return "redirect:/logout";
    }

    @PostMapping("/edit")
    public String editPagePOST(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        //przekierowanie do adresu url: /login
        return "redirect:/users";
    }

    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        //przekierowanie do adresu url: /login
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        //dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        //zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }

    @GetMapping("/users")
    public String usersPage(Model m) {
        m.addAttribute("users", dao.findAll());
        return "users";
    }
}

