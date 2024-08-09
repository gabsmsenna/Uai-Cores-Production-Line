package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.model.Client;
import com.gabriel.UaiCores_ProductionLine.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient (Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findClientById(Long id) {
        return this.clientRepository.findById(id);
    }

    public List<Client> findAllClient() {
        return this.clientRepository.findAll();
    }

    public Client updateClient (Long id, Client client) {
        Optional<Client> clientToBeUpdated = this.clientRepository.findById(id);

        if (clientToBeUpdated.isPresent()) {
            Client updatedClient = clientToBeUpdated.get();

            updatedClient.setName(client.getName());

            return clientRepository.save(updatedClient);

        } else {
            throw new  RuntimeException("Não foi possível atualizar esse client | ID (" + id + ") não encontrado");
        }
    }

    public void deleteClient (Long id) {
        if (this.clientRepository.existsById(id)) {
            this.clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Não foi possível excluir este cliente! | ID " + id + " não encontrado na aplicação");
        }
    }
}
