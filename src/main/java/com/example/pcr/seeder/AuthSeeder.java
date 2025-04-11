package com.example.pcr.seeder;

import com.example.pcr.entity.User;
import com.example.pcr.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthSeeder implements CommandLineRunner {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthSeeder(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (authRepository.count() > 0) {
            System.out.println("ℹ️ Users already exist. Skipping seeding.");
            return;
        }

        if (authRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setFullName("Admin Admin");
            admin.setPhoneNumber("08000000000");
            admin.setUsername("Admin");
            admin.setEmail("admin@peace.org");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);
            authRepository.save(admin);
            System.out.println("✅ Admin user seeded.");
        }

        if (authRepository.findByUsername("mediator").isEmpty()) {
            User mediator = new User();
            mediator.setFullName("Mediator Mediator");
            mediator.setPhoneNumber("08000000001");
            mediator.setUsername("mediator");
            mediator.setEmail("mediator@peace.org");
            mediator.setPassword(passwordEncoder.encode("mediator123"));
            mediator.setRole(User.Role.MEDIATOR);
            authRepository.save(mediator);
            System.out.println("✅ Mediator user seeded.");
        }
    }
}
