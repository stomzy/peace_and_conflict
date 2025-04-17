package com.example.pcr.controller;

import com.example.pcr.dto.ThemeDTO;
import com.example.pcr.entity.Theme;
import com.example.pcr.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<Theme> getTheme() {
        return ResponseEntity.ok(themeService.getCurrentTheme());
    }

    @PostMapping
    public ResponseEntity<Theme> saveTheme(@RequestBody ThemeDTO themeDTO) {
        return ResponseEntity.ok(themeService.saveTheme(themeDTO));
    }

    @PostMapping("/logo")
    public ResponseEntity<Map<String, String>> uploadLogo(@RequestParam("logo") MultipartFile logo) {
        try {
            String logoUrl = themeService.uploadLogo(logo);
            Map<String, String> response = new HashMap<>();
            response.put("logoUrl", logoUrl);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Logo upload failed"));
        }
    }
}
