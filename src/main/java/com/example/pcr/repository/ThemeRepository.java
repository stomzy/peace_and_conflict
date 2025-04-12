package com.example.pcr.repository;

import com.example.pcr.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findTopByOrderByIdDesc();
}
