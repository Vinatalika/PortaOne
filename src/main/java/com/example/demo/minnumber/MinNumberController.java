package com.example.demo.minnumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

@Controller
@SessionAttributes("uploadedFileName")
public class MinNumberController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/findMinNumber")
    public String findMinNumber(Model model) {
        MinNumber finder = new MinNumber();
        try {
            String fileName = (String) model.asMap().get("uploadedFileName");
            if (fileName == null) {
                model.addAttribute("message", "Файл не завантажено");
                return "upload";
            }
            String filePath = uploadPath + "/" + fileName;

            try {
                int min = finder.findMinNumber(filePath);
                model.addAttribute("message", "Мінімальне число у файлі: " + min);
            } catch (IOException e) {
                model.addAttribute("message", "Помилка при читанні файлу: " + e.getMessage());
            }
        } catch (Exception e) {
            model.addAttribute("message", "Помилка: " + e.getMessage());
        }

        return "upload";
    }
}
