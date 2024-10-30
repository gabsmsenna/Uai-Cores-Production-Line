package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.AdminUpdateOrderDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.CreateOrderDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.ExternalOfficerUpdateOrderDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.GetOrderDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Task.GetTaskDTO;
import com.gabriel.UaiCores_ProductionLine.model.AdminUser;
import com.gabriel.UaiCores_ProductionLine.model.Client;
import com.gabriel.UaiCores_ProductionLine.model.ExternalOfficer;
import com.gabriel.UaiCores_ProductionLine.model.Order;
import com.gabriel.UaiCores_ProductionLine.repository.ClientRepository;
import com.gabriel.UaiCores_ProductionLine.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Long createOrder(CreateOrderDTO orderDTO) {
        Client clientEntity = clientRepository.findById(orderDTO.clientId())
                .orElseThrow(() -> new EntityNotFoundException());

        var orderEntity = new Order(orderDTO.orderEntryDate(),
                orderDTO.orderDeliveryDate(),
                orderDTO.orderStatus(),
                clientEntity);

        var orderSaved = orderRepository.save(orderEntity);
        return orderSaved.getId();
    }

    public List<GetOrderDTO> getAllOrders() {

        try {
            Sort sort = Sort.by("orderEntryDate")   ;
            List<Order> ordersEntityList = orderRepository.findAll(sort);

            return ordersEntityList.stream()
                    .map(order -> new GetOrderDTO(
                            order.getOrderEntryDate(),
                            order.getOrderDeliveryDate(),
                            order.getOrderStatus(),
                            order.getClient() != null ? order.getClient().getName() : null
                    ))
                    .collect(Collectors.toUnmodifiableList());

        } catch (RuntimeException error) {
            System.err.println("Erro ao buscar os pedidos do sistema. " +
                    "Detalhes: " + error.getMessage());
            return Collections.emptyList();
        }

    }


    public Optional<GetOrderDTO> getOrderById(Long id) {

        try {
            Optional<Order> orderEntity = orderRepository.findById(id);
            return orderEntity.map(order -> new GetOrderDTO(
                    order.getOrderEntryDate(),
                    order.getOrderDeliveryDate(),
                    order.getOrderStatus(),
                    order.getClient() != null ? order.getClient().getName() : null
            ));
        } catch (RuntimeException error) {
            System.err.println("Erro ao buscar este pedido no sistema. " +
                    "Detalhes: " + error.getMessage());
            throw error;
        }

    }

    @Transactional
    public Order updateOrder (Long id, Object updateDto, Object user) {
        Order orderToBeUpdated = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        if (user instanceof AdminUser && updateDto instanceof AdminUpdateOrderDTO) {

            AdminUpdateOrderDTO adminDto = (AdminUpdateOrderDTO) updateDto;
            orderToBeUpdated.setOrderEntryDate(adminDto.orderEntryDate());
            orderToBeUpdated.setOrderDeliveryDate(adminDto.orderDeliveryDate());
            orderToBeUpdated.setOrderStatus(adminDto.orderStatus());
        } else if (user instanceof ExternalOfficer && updateDto instanceof ExternalOfficerUpdateOrderDTO) {
            ExternalOfficerUpdateOrderDTO externalOfficerUpdateOrderDTO = (ExternalOfficerUpdateOrderDTO) updateDto;
            orderToBeUpdated.setOrderStatus(externalOfficerUpdateOrderDTO.status());
        } else {
            throw new IllegalArgumentException("Permissão negada ou argumento inválido para esse tipo de usuário");
        }
        return orderRepository.save(orderToBeUpdated);
    }

    public void deleteOrderById(Long id) {

        try {
            orderRepository.deleteById(id);
        } catch (RuntimeException error) {
            System.err.println("Erro ao excluir o id " + id + ": " + error.getMessage());
        }
    }

}
