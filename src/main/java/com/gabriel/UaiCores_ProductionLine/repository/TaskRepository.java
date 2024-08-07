package com.gabriel.UaiCores_ProductionLine.repository;

import com.gabriel.UaiCores_ProductionLine.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
