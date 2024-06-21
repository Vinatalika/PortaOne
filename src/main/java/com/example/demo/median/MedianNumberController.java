package com.example.demo.median;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

@Controller
@SessionAttributes("uploadedFileName")
public class MedianNumberController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/findMedianNumber")
    public String findMedianNumber(Model model) {
        MedianNumber finder = new MedianNumber();
        try {
            String fileName = (String) model.asMap().get("uploadedFileName");
            if (fileName == null) {
                model.addAttribute("message", "Файл не завантажено");
                return "upload";
            }

            String filePath = uploadPath + "/" + fileName;
            try {
                double median = finder.findMedianNumber(filePath);
                model.addAttribute("message", "Медіана у файлі: " + median);
            } catch (IOException | IllegalArgumentException e) {
                model.addAttribute("message", "Помилка при читанні файлу: " + e.getMessage());
            }
        } catch (Exception e) {
            model.addAttribute("message", "Помилка: " + e.getMessage());
        }

        return "upload";
    }
}
