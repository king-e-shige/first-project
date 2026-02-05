package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails user, Model model) {
        if (user != null) {
            // お門番さんに教えてもらった名前を、画面に渡すで！
            model.addAttribute("username", user.getUsername());
        }
        return "index"; // index.html を表示する
    }
}
