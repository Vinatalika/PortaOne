package com.example.demo.sequence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.util.List;
@Controller
@SessionAttributes("uploadedFileName")
public class LongestIncreasingSequenceController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/findLongestIncreasingSequence")
    public String findLongestIncreasingSequence(Model model) {
        LongestIncreasingSequence finder = new LongestIncreasingSequence();
        try {
            String fileName = (String) model.asMap().get("uploadedFileName");
            if (fileName == null) {
                model.addAttribute("message", "Файл не завантажено");
                return "upload";
            }
            String filePath = uploadPath + "/" + fileName;

            try {
                List<Integer> sequence = finder.findLongestIncreasingSequence(filePath);
                model.addAttribute("message", "Найдовша зростаюча послідовність у файлі: " + sequence);
            } catch (IOException | IllegalArgumentException e) {
                model.addAttribute("message", "Помилка при читанні файлу: " + e.getMessage());
            }
        } catch (Exception e) {
            model.addAttribute("message", "Помилка: " + e.getMessage());
        }

        return "upload";
    }
}
