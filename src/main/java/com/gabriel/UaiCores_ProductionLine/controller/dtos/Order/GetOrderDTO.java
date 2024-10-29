package com.gabriel.UaiCores_ProductionLine.controller.dtos.Order;

import com.gabriel.UaiCores_ProductionLine.model.Client;

import java.time.Instant;
import java.time.LocalDate;

public record GetOrderDTO (
        LocalDate orderEntryDate,
        LocalDate orderDeliveryDate,
        String orderStatus,
        String clientName
) {}