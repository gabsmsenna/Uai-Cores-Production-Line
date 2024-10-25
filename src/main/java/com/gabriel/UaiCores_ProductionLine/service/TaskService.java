package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.CreateTaskDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.GetTaskDTO;
import com.gabriel.UaiCores_ProductionLine.model.Order;
import com.gabriel.UaiCores_ProductionLine.model.Task;
import com.gabriel.UaiCores_ProductionLine.repository.OrderRepository;
import com.gabriel.UaiCores_ProductionLine.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private OrderRepository orderRepository;


    public Long createTask(CreateTaskDTO createTask) {
        var order = orderRepository.findById(createTask.orderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));

        var taskEntity = new Task(
                createTask.amount(),
                createTask.name(),
                createTask.description(),
                createTask.verseColor(),
                createTask.material(),
                createTask.taskStatus(),
                order
        );
        var taskSaved = taskRepository.save(taskEntity);
        return  taskSaved.getId();
    }

    public List<GetTaskDTO> getAllTasks() {

        try {
            List<Task> taskList = taskRepository.findAll();
            return taskList.stream()
                    .map(task -> new GetTaskDTO(
                            task.getId(),
                            task.getAmount(),
                            task.getName(),
                            task.getDescription(),
                            task.getVerseColor(),
                            task.getMaterial(),
                            task.getTaskStatus(),
                            task.getOrder() != null ? task.getOrder().getId() : null
                    ))
                    .collect(Collectors.toUnmodifiableList());

        } catch (RuntimeException error) {
            System.err.println("Erro ao buscar os serviços no sistema. " +
                    "Detalhes: " + error.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<Task> getTaskById(Long id) {

        Task task = taskRepository.findById(id).get();
        return Optional.of(task);

    }

    public Task updateTask(Long id, Task task) {

        return null;

    }
}