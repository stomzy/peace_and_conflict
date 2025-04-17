package com.example.pcr.service;

import com.example.pcr.dto.ThemeDTO;
import com.example.pcr.entity.Theme;
import com.example.pcr.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Theme getCurrentTheme() {
        return themeRepository.findAll().stream().findFirst().orElse(new Theme("#ffffff", null));
    }

    public Theme saveTheme(ThemeDTO themeDTO) {
        Theme theme = themeRepository.findAll().stream().findFirst().orElse(new Theme());
        theme.setPrimaryColor(themeDTO.getPrimaryColor());
        theme.setLogoUrl(themeDTO.getLogoUrl());
        return themeRepository.save(theme);
    }

    public String uploadLogo(MultipartFile logoFile) throws IOException {
        String filename = UUID.randomUUID() + "_" + logoFile.getOriginalFilename();
        Path path = Paths.get("uploads/logos", filename);
        Files.createDirectories(path.getParent());
        Files.write(path, logoFile.getBytes());

        return "/uploads/logos/" + filename;
    }
}
