package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.CreateTaskDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.GetTaskDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.UpdateTaskDTO;
import com.gabriel.UaiCores_ProductionLine.model.Order;
import com.gabriel.UaiCores_ProductionLine.model.Task;
import com.gabriel.UaiCores_ProductionLine.service.TaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> postTask(@RequestBody CreateTaskDTO createTaskDTO) {

        var taskId = taskService.createTask(createTaskDTO);
        return ResponseEntity.created(URI.create("/api/v1/task/" + taskId)).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        List<GetTaskDTO> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Optional<GetTaskDTO> task = taskService.getTaskById(id);
        return task.isPresent() ? ResponseEntity.ok(task.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody UpdateTaskDTO task) {

        try {
            taskService.updateTask(id, task);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException error) {
            error.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder (@PathVariable Long id) {

        try {
            taskService.deleteTaskById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
