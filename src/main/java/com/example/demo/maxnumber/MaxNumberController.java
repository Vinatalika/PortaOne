package com.example.demo.maxnumber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

@Controller
@SessionAttributes("uploadedFileName")
public class MaxNumberController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/findMaxNumber")
    public String findMaxNumber(Model model) {
        MaxNumber finder = new MaxNumber();
        try {
            String fileName = (String) model.getAttribute("uploadedFileName");
            if (fileName == null) {
                model.addAttribute("message", "Файл не завантажено");
                return "upload";
            }

            String filePath = uploadPath + "/" + fileName;
            int max = finder.findMaxNumber(filePath);
            model.addAttribute("message", "Максимальне число у файлі: " + max);
        } catch (IOException e) {
            model.addAttribute("message", "Помилка при читанні файлу: " + e.getMessage());
        }

        return "upload";
    }
}