package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.ExternalOfficer.CreateExternalOfficerDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.ExternalOfficer.UpdateExternalOfficerDTO;
import com.gabriel.UaiCores_ProductionLine.model.ExternalOfficer;
import com.gabriel.UaiCores_ProductionLine.service.ExternalOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/officer")
public class ExternalOfficerController {

    @Autowired
    private ExternalOfficerService externalOfficerService;

    @PostMapping()
    public ResponseEntity<ExternalOfficer> addExternalOfficer(@RequestBody CreateExternalOfficerDTO externalOfficerDTO) {
        var userId = externalOfficerService.createExternalOfficer(externalOfficerDTO);

        return ResponseEntity.created(URI.create("/v1/officer" + userId.toString())).build();
    }

    @GetMapping()
    public ResponseEntity<List<ExternalOfficer>> getAll() {
        if (externalOfficerService.getAllExternalOfficers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ExternalOfficer> externalOfficers = externalOfficerService.getAllExternalOfficers();
        return new ResponseEntity<>(externalOfficers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var optionalExternalOfficer = externalOfficerService.getExternalOfficerDTOById(id);

        if (optionalExternalOfficer.isPresent()) {
            return ResponseEntity.ok(optionalExternalOfficer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateOfficer (@PathVariable Long id,
                                               @RequestBody UpdateExternalOfficerDTO externalOfficerDTO) {

        externalOfficerService.updateExOfficer(id, externalOfficerDTO);
        return ResponseEntity.noContent().build();
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
