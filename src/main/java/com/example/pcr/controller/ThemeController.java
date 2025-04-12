package com.example.pcr.controller;

import com.example.pcr.entity.Theme;
import com.example.pcr.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    private final String uploadDir = "/uploads/";

    @GetMapping
    public Theme getTheme() {
        return themeService.getCurrentTheme();
    }

    @PostMapping("/logo")
    public ResponseEntity<String> uploadLogo(@RequestParam("logo") MultipartFile file) throws IOException {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("No file uploaded", HttpStatus.BAD_REQUEST);
            }

            // Validate file size (limit to 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                throw new MultipartException("File size exceeds limit");
            }

            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            String logoUrl = path.toString();

            Theme theme = themeService.getCurrentTheme();
            theme.setLogoUrl(logoUrl);
            themeService.saveTheme(theme);

            return ResponseEntity.status(HttpStatus.CREATED).body("Logo uploaded successfully");
        } catch (MultipartException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
