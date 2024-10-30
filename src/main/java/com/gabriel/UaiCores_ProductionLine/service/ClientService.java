package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Client.ClientOrdersDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.GetTasksOnOrderDTO;
import com.gabriel.UaiCores_ProductionLine.model.Client;
import com.gabriel.UaiCores_ProductionLine.model.Task;
import com.gabriel.UaiCores_ProductionLine.repository.ClientRepository;
import com.gabriel.UaiCores_ProductionLine.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TaskRepository taskRepository;

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

    public List<ClientOrdersDTO> listOrders(Long clientId) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        return client.getOrders().stream()
                .map(order -> {

                    List<Task> tasks = taskRepository.findByOrderId(order.getId());

                    List<GetTasksOnOrderDTO> taskDTOs = tasks.stream()
                            .map(task -> new GetTasksOnOrderDTO(task.getAmount(), task.getName(), task.getTaskStatus()))
                            .toList();

                    return new ClientOrdersDTO(
                            order.getOrderEntryDate(),
                            order.getOrderDeliveryDate(),
                            order.getOrderStatus(),
                            taskDTOs
                    );
                })
                .toList();
    }

}
