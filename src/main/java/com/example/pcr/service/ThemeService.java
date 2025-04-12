package com.example.pcr.service;

import com.example.pcr.entity.Theme;
import com.example.pcr.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public Theme getCurrentTheme() {
        return themeRepository.findTopByOrderByIdDesc();
    }

    public Theme saveTheme(Theme theme) {
        return themeRepository.save(theme);
    }
}
