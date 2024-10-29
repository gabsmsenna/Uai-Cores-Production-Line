package com.gabriel.UaiCores_ProductionLine.controller.dtos.Task;

public record GetTaskDTO(
        Integer amount,
        String name,
        String description,
        String verseColor,
        String material,
        String taskStatus,
        Long orderId
        ) {
}
