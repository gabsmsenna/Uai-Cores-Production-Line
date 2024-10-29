package com.gabriel.UaiCores_ProductionLine.controller;

import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.CreateOrderDTO;
import com.gabriel.UaiCores_ProductionLine.controller.dtos.Order.GetOrderDTO;
import com.gabriel.UaiCores_ProductionLine.model.Order;
import com.gabriel.UaiCores_ProductionLine.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<Order> saveOrder(@RequestBody CreateOrderDTO orderDTO) {
        var orderId = orderService.createOrder(orderDTO);

        return ResponseEntity.created(URI.create("/v1/order" + orderId)).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<GetOrderDTO> ordersDtos = orderService.getAllOrders();

        if (ordersDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ordersDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrdersById (@PathVariable Long id) {

        Optional<GetOrderDTO> order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder (@PathVariable Long id, @RequestBody Order order) {

        try {
            Order updatedOrder = orderService.updateOrder(id, order);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (RuntimeException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder (@PathVariable Long id) {

        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
