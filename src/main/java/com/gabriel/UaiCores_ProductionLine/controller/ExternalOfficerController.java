package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.model.ExternalOfficer;
import com.gabriel.UaiCores_ProductionLine.service.ExternalOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/officer")
public class ExternalOfficerController {

    @Autowired
    private ExternalOfficerService externalOfficerService;

    @PostMapping("/addofficer")
    public ResponseEntity<ExternalOfficer> addExternalOfficer(@RequestBody ExternalOfficer externalOfficer) {

        try {
            ExternalOfficer externalOfficerObj = externalOfficerService.createExternalOfficer(externalOfficer);
//            var uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                    .path("/{id}").buildAndExpand(externalOfficerObj.getId()).toUri();
            return new ResponseEntity<>(externalOfficerObj, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(externalOfficer);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExternalOfficer>> getAll() {
        if (externalOfficerService.getAllExternalOfficers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ExternalOfficer> externalOfficers = externalOfficerService.getAllExternalOfficers();
        return new ResponseEntity<>(externalOfficers, HttpStatus.OK);
    }

    @GetMapping("/getofficerbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<ExternalOfficer> optionalExternalOfficer = externalOfficerService.getExternalOfficerById(id);

        if (optionalExternalOfficer.isPresent()) {
            return new ResponseEntity<>(optionalExternalOfficer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExternalOfficer> updateOfficer (@PathVariable Long id, @RequestBody ExternalOfficer externalOfficer) {
        try {
            ExternalOfficer updatedOfficerResult = externalOfficerService.updateExOfficer(id, externalOfficer);
            return new ResponseEntity<>(updatedOfficerResult, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(externalOfficer);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOfficer(@PathVariable Long id) {
        Optional<ExternalOfficer> existingOfficer = externalOfficerService.getExternalOfficerById(id);
        if (existingOfficer.isPresent()) {
            this.externalOfficerService.deleteExternalOfficer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
