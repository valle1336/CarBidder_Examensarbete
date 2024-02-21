package com.alex.carbider.CarBider.controllers;

import com.alex.carbider.CarBider.config.PasswordConfig;
import com.alex.carbider.CarBider.entities.user.UserEntity;
import com.alex.carbider.CarBider.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;

    @Autowired
    public UserController(UserRepository userRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/register")
    public String showUser(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @Valid UserEntity userEntity,
            BindingResult result
            )
    {
        if(result.hasErrors()) {
            return "register";
        }
        userEntity.setPassword(passwordConfig.bCryptPasswordEncoder().encode(userEntity.getPassword()));

        userEntity.setAccountEnabled(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);

        userRepository.save(userEntity);

        return "redirect:/";
    }
}
