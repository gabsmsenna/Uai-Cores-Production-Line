package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.CreateOrderDTO;
import com.gabriel.UaiCores_ProductionLine.model.Client;
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

    public List<Order> getAllOrders() {

        try {
            Sort sort = Sort.by("orderEntryDate")   ;
            return  orderRepository.findAll(sort);
        } catch (RuntimeException error) {
            System.err.println("Erro ao buscar os pedidos do sistema. " +
                    "Detalhes: " + error.getMessage());
            return Collections.emptyList();
        }

    }


    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro ao buscar o pedido de id " + id));

    }

    @Transactional
    public Order updateOrder (Long id, Order order) {
        Optional<Order> orderToBeUpdated = orderRepository.findById(id);

        if (orderToBeUpdated.isPresent()) {

            Order orderUpdated = orderToBeUpdated.get();
            orderUpdated.setOrderStatus(order.getOrderStatus());
            orderUpdated.setOrderEntryDate(order.getOrderEntryDate());
            orderUpdated.setOrderDeliveryDate(order.getOrderDeliveryDate());
            orderUpdated.setTasks(order.getTasks());

            return orderRepository.save(orderUpdated);
        } else {

            throw new RuntimeException("Não foi possível atualizar esse funcionário | ID (" + id + ") não encontrado");
        }
    }

    public void deleteOrderById(Long id) {

        try {
            orderRepository.deleteById(id);
        } catch (RuntimeException error) {
            System.err.println("Erro ao excluir o id " + id + ": " + error.getMessage());
        }
    }


}
