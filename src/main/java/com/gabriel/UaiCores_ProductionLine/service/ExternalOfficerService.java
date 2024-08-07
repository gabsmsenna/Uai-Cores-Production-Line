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

    public Optional<ExternalOfficer> getExternalOfficerById(Long id) {
        return externalOfficerRepository.findById(id);
    }

    public ExternalOfficer updateExOfficer(Long id, ExternalOfficer externalOfficerObj) {
        Optional<ExternalOfficer> officerToBeUpdated = externalOfficerRepository.findById(id);

        if (officerToBeUpdated.isPresent()) {
            ExternalOfficer officerUpdated = officerToBeUpdated.get();

            officerUpdated.setName(externalOfficerObj.getName());
            officerUpdated.setLogin(externalOfficerObj.getLogin());
            officerUpdated.setPassword(externalOfficerObj.getPassword());

            return externalOfficerRepository.save(officerUpdated);
        } else {

            throw new  RuntimeException("Não foi possível atualizar esse funcionário | ID (" + id + ") não encontrado");
        }
    }


    public void deleteExternalOfficer(Long id) {
        if (externalOfficerRepository.existsById(id)) {
            externalOfficerRepository.deleteById(id);
        } else throw new RuntimeException("Erro ao excluir o objeto" + "ID: " + id);
    }
}