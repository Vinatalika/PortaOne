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
public class LongestDecreasingSequenceController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/findLongestDecreasingSequence")
    public String findLongestDecreasingSequence(Model model) {
        LongestDecreasingSequence finder = new LongestDecreasingSequence();
        try {
            String fileName = (String) model.asMap().get("uploadedFileName");
            if (fileName == null) {
                model.addAttribute("message", "Файл не завантажено");
                return "upload";
            }
            String filePath = uploadPath + "/" + fileName;

            try {
                List<Integer> sequence = finder.findLongestDecreasingSequence(filePath);
                model.addAttribute("message", "Найдовша зменшуюча послідовність у файлі: " + sequence);
            } catch (IOException | IllegalArgumentException e) {
                model.addAttribute("message", "Помилка при читанні файлу: " + e.getMessage());
            }
        } catch (Exception e) {
            model.addAttribute("message", "Помилка: " + e.getMessage());
        }

        return "upload";
    }
}
