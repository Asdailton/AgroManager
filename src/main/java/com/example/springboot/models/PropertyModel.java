package com.example.springboot.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PROPERTIES")
public class PropertyModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String location;
    private BigDecimal size;
    private String soilType;
    private String cropTypes;

    @Lob
    @ElementCollection
    private List<byte[]> photos;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public void setId(UUID uuid) {
    }

    // Getters and Setters
}
