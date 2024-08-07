package com.gabriel.UaiCores_ProductionLine.service;

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

    public ExternalOfficer createExternalOfficer(ExternalOfficer externalOfficerObj) {
        return externalOfficerRepository.save(externalOfficerObj);
    }

    public List<ExternalOfficer> getAllExternalOfficers() {
        return externalOfficerRepository.findAll();
    }

    public ExternalOfficer getExternalOfficerById(Long id) {
        Optional<ExternalOfficer> externalOfficerObj = externalOfficerRepository.findById(id);
        return externalOfficerObj.orElseThrow(() ->
                new RuntimeException("Erro ao encontrar o objeto" + "ID: " + id));
    }

    public ExternalOfficer updateExOfficer(Long id, ExternalOfficer externalOfficerObj) {
        ExternalOfficer entityExOfficer = externalOfficerRepository.getReferenceById(id);
        updateData(entityExOfficer, externalOfficerObj);
        return externalOfficerRepository.save(entityExOfficer);
    }

    private void updateData(ExternalOfficer newEntity, ExternalOfficer externalOfficerObj) {
        newEntity.setName(externalOfficerObj.getName());
        newEntity.setLogin(externalOfficerObj.getLogin());
        newEntity.setPassword(externalOfficerObj.getPassword());
    }

    public void deleteExternalOfficer(Long id) {
        if (externalOfficerRepository.existsById(id)) {
            externalOfficerRepository.deleteById(id);
        } else throw new RuntimeException("Erro ao excluir o objeto" + "ID: " + id);
    }
}