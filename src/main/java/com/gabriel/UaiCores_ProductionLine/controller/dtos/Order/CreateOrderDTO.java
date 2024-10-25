package com.gabriel.UaiCores_ProductionLine.controller.dtos.Order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreateOrderDTO (
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate orderEntryDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate orderDeliveryDate,
                              String orderStatus,
                              Long clientId){
}
