package com.example.demo.controller;

import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.User;
import com.example.demo.UserRepository;

import jakarta.validation.Valid;

// @Controller
public class HelloController {

    @GetMapping("/profile/{id}")
    public String showProfile(
            @PathVariable("id") String id,
            @RequestParam(value = "name", defaultValue = "名無し") String name,
            Model model) {

        // Modelの中にデータをセットする (キー, 値)
        model.addAttribute("userId", id);
        model.addAttribute("userName", name);

        // templates/profile.html を呼び出す
        return "profile";
    }

    @Autowired // データベース操作用の道具を注入
    private UserRepository userRepository;

    @GetMapping("/old-form")
    public String showForm(User user, Model model) {
        model.addAttribute("allUsers", userRepository.findAll());
        return "input-form";
    }

    @PostMapping("/confirm")
    public String confirm(@Valid User user, BindingResult result, 
                        @RequestParam("imageFile") MultipartFile imageFile, Model model) throws Exception {
        
        if (result.hasErrors()) {
            return "input-form";
        }

        // 画像が選択されている場合のみ保存処理を行う
        if (!imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            Path filePath = Paths.get("src/main/resources/static/images/" + fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            user.setImageName(fileName); // ファイル名をDBに記録
        }

        userRepository.save(user);
        return "redirect:/form";
    }

    // 削除機能
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/form"; // 削除後、入力画面にリダイレクト
    }

    // 編集画面の表示
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "input-form"; // 同じフォームを編集用として使う
    }

}
