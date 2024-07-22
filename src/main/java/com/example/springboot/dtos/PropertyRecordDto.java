package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PropertyRecordDto(
        UUID id,
        @NotBlank(message = "Name is mandatory") String name,
        @NotBlank(message = "Location is mandatory") String location,
        @NotNull(message = "Size is mandatory") BigDecimal size,
        @NotBlank(message = "Soil type is mandatory") String soilType,
        @NotBlank(message = "Crop types are mandatory") String cropTypes,
        List<byte[]> photos,
        @NotNull(message = "User ID is mandatory") UUID userId
) {}
