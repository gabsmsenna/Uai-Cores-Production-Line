package com.gabriel.UaiCores_ProductionLine.controller.dtos.Client;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.GetTasksOnOrderDTO;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public record ClientOrdersDTO (LocalDate orderEntryDate,
                               LocalDate orderDeliveryDate,
                               String orderStatus,
                               List<GetTasksOnOrderDTO> tasksOnOrderDTO){
}
