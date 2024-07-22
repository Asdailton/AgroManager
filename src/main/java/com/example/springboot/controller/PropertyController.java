package com.example.springboot.controller;

import com.example.springboot.dtos.PropertyRecordDto;
import com.example.springboot.models.PropertyModel;
import com.example.springboot.repositories.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    PropertyRepository propertyRepository;

    @PostMapping
    public ResponseEntity<PropertyModel> createProperty(@RequestBody @Valid PropertyRecordDto propertyRecordDto) {
        var propertyModel = new PropertyModel();
        BeanUtils.copyProperties(propertyRecordDto, propertyModel);
        propertyModel.setId(UUID.randomUUID()); // Ensure the ID is set to a new UUID
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyRepository.save(propertyModel));
    }

    @GetMapping
    public ResponseEntity<List<PropertyModel>> getAllProperties() {
        return ResponseEntity.status(HttpStatus.OK).body(propertyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPropertyById(@PathVariable(value = "id") UUID id) {
        Optional<PropertyModel> propertyModelOptional = propertyRepository.findById(id);
        if (propertyModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(propertyModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProperty(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid PropertyRecordDto propertyRecordDto) {
        Optional<PropertyModel> propertyModelOptional = propertyRepository.findById(id);
        if (propertyModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found.");
        }
        var propertyModel = propertyModelOptional.get();
        BeanUtils.copyProperties(propertyRecordDto, propertyModel);
        propertyModel.setId(id); // Ensure the ID remains the same
        return ResponseEntity.status(HttpStatus.OK).body(propertyRepository.save(propertyModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProperty(@PathVariable(value = "id") UUID id) {
        Optional<PropertyModel> propertyModelOptional = propertyRepository.findById(id);
        if (propertyModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Property not found.");
        }
        propertyRepository.delete(propertyModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Property deleted successfully.");
    }
}
