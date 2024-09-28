package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.ExternalOfficer.CreateExternalOfficerDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.ExternalOfficer.UpdateExternalOfficerDTO;
import com.gabriel.UaiCores_ProductionLine.model.ExternalOfficer;
import com.gabriel.UaiCores_ProductionLine.repository.ExternalOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalOfficerService {

    @Autowired
    private ExternalOfficerRepository externalOfficerRepository;

    public Long createExternalOfficer(CreateExternalOfficerDTO externalOfficerDTO) {
        var entityExternalOfficer = new ExternalOfficer(externalOfficerDTO.name(),
                externalOfficerDTO.login(),
                externalOfficerDTO.password());

        var userSaved = externalOfficerRepository.save(entityExternalOfficer);
        return userSaved.getId();
    }

    public List<ExternalOfficer> getAllExternalOfficers() {
        return externalOfficerRepository.findAll();
    }

    public Optional<ExternalOfficer> getExternalOfficerById(Long id) {
        var externalOfficer = externalOfficerRepository.findById(id);
        return externalOfficer;
    }

    public void updateExOfficer(Long id,
                                UpdateExternalOfficerDTO updateExternalOfficerDTO) {

       var officerExists = externalOfficerRepository.findById(id);
       if (officerExists.isPresent()) {
            var externalOfficer = officerExists.get();

            if (updateExternalOfficerDTO.password() != null) {
                externalOfficer.setPassword(updateExternalOfficerDTO.password());
            }

            externalOfficerRepository.save(externalOfficer);
       }

    }


    public void deleteExternalOfficer(Long id) {
        if (externalOfficerRepository.existsById(id)) {
            externalOfficerRepository.deleteById(id);
        } else throw new RuntimeException("Erro ao excluir o objeto" + "ID: " + id);
    }
}