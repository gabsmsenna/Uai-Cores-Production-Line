package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.model.ExternalOfficer;
import com.gabriel.UaiCores_ProductionLine.service.ExternalOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/externalofficer")
public class ExternalOfficerController {

    @Autowired
    private ExternalOfficerService externalOfficerService;

    @PostMapping()
    public ResponseEntity<ExternalOfficer> addExternalOfficer(@RequestBody ExternalOfficer externalOfficer) {

        try {
            ExternalOfficer externalOfficerObj = externalOfficerService.createExternalOfficer(externalOfficer);
            var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(externalOfficerObj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(externalOfficer);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExternalOfficer>> getAll() {
        if (externalOfficerService.getAllExternalOfficers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ExternalOfficer> externalOfficers = externalOfficerService.getAllExternalOfficers();
        return new ResponseEntity<>(externalOfficers, HttpStatus.OK);
    }
}
