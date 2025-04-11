package com.example.pcr.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegisterRequest {
    @NotBlank(message = "Name is required")
    public String fullName;
    @NotBlank(message = "Phone number is required")
    public String phoneNumber;
    @NotBlank(message = "Username is required")
    public String username;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    public String email;
    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password is required")
    public String password;
    @NotBlank(message = "Role is required")
    public String role;

    public @NotBlank(message = "Name is required") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank(message = "Name is required") String fullName) {
        this.fullName = fullName;
    }

    public @NotBlank(message = "Phone number is required") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank(message = "Phone number is required") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotBlank(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") String username) {
        this.username = username;
    }

    public @Email(message = "Email must be valid") @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email must be valid") @NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @Size(min = 6, message = "Password must be at least 6 characters") @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Password must be at least 6 characters") @NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Role is required") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role is required") String role) {
        this.role = role;
    }
}
