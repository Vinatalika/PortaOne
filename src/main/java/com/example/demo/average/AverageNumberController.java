package com.example.demo.average;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
@Controller
@SessionAttributes("uploadedFileName")
public class AverageNumberController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/findAverageNumber")
    public String findAverageNumber(Model model) {
        AverageNumber finder = new AverageNumber();
        try {
            String fileName = (String) model.asMap().get("uploadedFileName");
            if (fileName == null) {
                model.addAttribute("message", "Файл не завантажено");
                return "upload";
            }

            String filePath = uploadPath + "/" + fileName;
            try {
                double average = finder.findAverageNumber(filePath);
                model.addAttribute("message", "Середнє арифметичне число у файлі: " + average);
            } catch (IOException e) {
                model.addAttribute("message", "Помилка при читанні файлу: " + e.getMessage());
            }
        } catch (Exception e) {
            model.addAttribute("message", "Помилка: " + e.getMessage());
        }

        return "upload";
    }
}
