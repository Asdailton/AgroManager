package com.example.springboot.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserRecordDto(
        UUID id,
        @NotBlank(message = "Username is mandatory") String username,
        @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email,
        @NotBlank(message = "Password is mandatory") String password,
        @NotBlank(message = "Role is mandatory") String role
) {}
