package com.example.springboot.controller;

import com.example.springboot.dtos.FieldRecordDto;
import com.example.springboot.models.FieldModel;
import com.example.springboot.repositories.FieldRepository;
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
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    FieldRepository fieldRepository;

    @PostMapping
    public ResponseEntity<FieldModel> createField(@RequestBody @Valid FieldRecordDto fieldRecordDto) {
        var fieldModel = new FieldModel();
        BeanUtils.copyProperties(fieldRecordDto, fieldModel);
        fieldModel.setId(UUID.randomUUID()); // Ensure the ID is set to a new UUID
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldRepository.save(fieldModel));
    }

    @GetMapping
    public ResponseEntity<List<FieldModel>> getAllFields() {
        return ResponseEntity.status(HttpStatus.OK).body(fieldRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFieldById(@PathVariable(value = "id") UUID id) {
        Optional<FieldModel> fieldModelOptional = fieldRepository.findById(id);
        if (fieldModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fieldModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateField(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid FieldRecordDto fieldRecordDto) {
        Optional<FieldModel> fieldModelOptional = fieldRepository.findById(id);
        if (fieldModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field not found.");
        }
        var fieldModel = fieldModelOptional.get();
        BeanUtils.copyProperties(fieldRecordDto, fieldModel);
        fieldModel.setId(id); // Ensure the ID remains the same
        return ResponseEntity.status(HttpStatus.OK).body(fieldRepository.save(fieldModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteField(@PathVariable(value = "id") UUID id) {
        Optional<FieldModel> fieldModelOptional = fieldRepository.findById(id);
        if (fieldModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Field not found.");
        }
        fieldRepository.delete(fieldModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Field deleted successfully.");
    }
}
