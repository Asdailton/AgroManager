package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record FieldRecordDto(
        UUID id,
        @NotBlank(message = "Name is mandatory") String name,
        @NotNull(message = "Size is mandatory") BigDecimal size,
        @NotBlank(message = "Crop type is mandatory") String cropType,
        @NotBlank(message = "Planting date is mandatory") String plantingDate,
        @NotNull(message = "Property ID is mandatory") UUID propertyId
) {}
