package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.model.Order;
import com.gabriel.UaiCores_ProductionLine.model.Task;
import com.gabriel.UaiCores_ProductionLine.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {

        try {

            List<Task> taskList = taskRepository.findAll();
            return Collections.unmodifiableList(taskList);

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