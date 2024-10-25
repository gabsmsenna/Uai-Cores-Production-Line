package com.gabriel.UaiCores_ProductionLine.controller.dtos.Task;

public record GetTasksOnOrderDTO(Integer amount,
                                 String name,
                                 String taskStatus) {
}
