package com.example.springboot.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_FIELDS")
public class FieldModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyModel property;

    private String name;
    private BigDecimal size;
    private String cropType;
    private String plantingDate;

    public void setId(UUID uuid) {
    }

    // Getters and Setters
}
