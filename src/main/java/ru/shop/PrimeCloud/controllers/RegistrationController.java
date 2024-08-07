package ru.shop.PrimeCloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shop.PrimeCloud.dao.JdbcSingerDao;
import ru.shop.PrimeCloud.models.User;

@Controller
public class RegistrationController {

    private final JdbcSingerDao jdbcSingerDao;

    public RegistrationController(JdbcSingerDao jdbcSingerDao) {
        this.jdbcSingerDao = jdbcSingerDao;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/createUser")
    public String reg1(@ModelAttribute User user, Model model) {
        if (jdbcSingerDao.findByEmail(user.getEmail())) {
            System.out.println("That email is already registered");
        }

        // create user and add to database

        return "login";
    }
}
