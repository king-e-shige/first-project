package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.MemberService;

@Controller
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    // 入力画面を表示する
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("members", service.getAllMembers());
        return "input-form"; // input-form.html を探してね！という指示
    }

    // ボタンが押された時に名前を保存する
    @PostMapping("/add")
    public String addMember(@RequestParam("name") String name) {
        service.saveMember(name);
        return "redirect:/form"; // 保存したらまた画面に戻る
    }
}
