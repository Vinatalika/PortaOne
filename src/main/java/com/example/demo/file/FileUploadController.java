package com.example.demo.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@SessionAttributes("uploadedFileName")
public class FileUploadController {

    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";
    private String uploadedFileName;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("uploadedFileName", uploadedFileName);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Файл порожній");
            return "upload";
        }

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            file.transferTo(filePath);

            uploadedFileName = file.getOriginalFilename();
            model.addAttribute("message", "Файл завантажено успішно: " + filePath.toString());
            model.addAttribute("uploadedFileName", uploadedFileName);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Помилка при завантаженні файлу: " + e.getMessage());
        }

        return "upload";
    }
}
