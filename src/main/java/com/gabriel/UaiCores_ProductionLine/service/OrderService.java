package com.gabriel.UaiCores_ProductionLine.service;

import com.gabriel.UaiCores_ProductionLine.model.Order;
import com.gabriel.UaiCores_ProductionLine.repository.OrderRepository;
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

    @Transactional
    public Order saveOrder (Order order) {
        order.setId(null);
        order = orderRepository.save(order);
        return order;
    }

    public List<Order> getAllOrders() {

        try {
            Sort sort = Sort.by("orderEntryDate").descending();
            List<Order> ordersList = orderRepository.findAll(sort);
            return Collections.unmodifiableList(ordersList);
        } catch (RuntimeException error) {
            System.err.println("Erro ao buscar os pedidos do sistema. " +
                    "Detalhes: " + error.getMessage());
            return Collections.emptyList();
        }

    }


    public Optional<Order> getOrderById(Long id) {

        Optional<Order> orderById = orderRepository.findById(id);
        return orderById;

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
