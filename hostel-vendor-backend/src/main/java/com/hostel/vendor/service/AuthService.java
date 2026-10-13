
package com.hostel.vendor.service;

import java.util.Locale;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hostel.vendor.entity.HostelUser;
import com.hostel.vendor.repository.HostelUserRepository;

@Service
public class AuthService {

    private final HostelUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            HostelUserRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public HostelUser register(HostelUser user) {
        String email = user.getEmail()
                .trim().toLowerCase(Locale.ROOT);

        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email is already registered");
        }

        if (user.getName() == null
                || user.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Name is required");
        }

        String role = user.getRole()
                .trim().toUpperCase(Locale.ROOT);

        if (!role.equals("WARDEN")
                && !role.equals("STUDENT")) {
            throw new IllegalArgumentException(
                    "Role must be WARDEN or STUDENT");
        }

        if (user.getPassword() == null
                || user.getPassword().length() < 8) {
            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters");
        }

        user.setEmail(email);
        user.setRole(role);
        user.setPassword(
                passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public HostelUser login(String email, String password) {
        HostelUser user = repository.findByEmail(
                email.trim().toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid email or password"));

        if (password == null
                || !passwordEncoder.matches(
                        password, user.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        return user;
    }
}
