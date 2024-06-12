package logic.service.impl;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import domain.Order;
import domain.Rate;
import domain.TaskState;
import logic.service.OrderService;
import org.junit.jupiter.api.Test;
import util.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    public void testCreateOrder() throws DatabaseOperationException {
        // Arrange
        OrderService orderService = ApplicationContext.getOrderService();
        Order order = Order.builder()
                .description("A new order")
                .price(100.0)
                .time(LocalDateTime.now())
                .taskState(TaskState.AWAITING_EXPERT_SELECTION)
                .rate(Rate.GOOD)
                .build();

        // Act
        Order savedOrder = orderService.saveOrUpdate(order);

        // Assert
        assertNotNull(savedOrder,"Order should be created");
        assertEquals(0.0, savedOrder.getPrice(), 100.0,"Price should match"  );
        assertEquals(savedOrder.getRate(), Rate.GOOD, "Rate should match");
    }

    @Test
    public void testReadOrder() throws DatabaseOperationException, NotFoundException {
        // Arrange
        OrderService orderService = ApplicationContext.getOrderService();
        Order order = createTestOrder();
        Long id = orderService.saveOrUpdate(order).getId();

        // Act
        Order foundOrder = orderService.findById(id);

        // Assert
        assertNotNull(foundOrder,"Order should be found");
    }

    @Test
    public void testUpdateOrder() throws DatabaseOperationException {
        // Arrange
        OrderService orderService = ApplicationContext.getOrderService();
        Order order = createTestOrder();
        order.setId(1L);
        Order savedOrder = orderService.saveOrUpdate(order);

        savedOrder.setDescription("Updated 2 order description ");
        savedOrder.setRate(Rate.VERY_BAD);

        String test = "Updated 2 order description";

        // Act
        Order updatedOrder = orderService.saveOrUpdate(savedOrder);

        // Assert
        assertEquals(updatedOrder.getDescription().trim(), test.trim(),"Description should be updated"  );
        assertEquals(updatedOrder.getRate(), Rate.VERY_BAD ,"Rate should be updated" );
    }

    @Test
    public void testDeleteOrderById() throws DatabaseOperationException {
        // Arrange
        OrderService orderService = ApplicationContext.getOrderService();
        Order order = createTestOrder();
        Order savedOrder = orderService.saveOrUpdate(order);

        // Act
        orderService.delete(savedOrder);

        // Assert
        try {
            orderService.findById(savedOrder.getId());
            fail("Order should not be found after being deleted");
        } catch (NotFoundException expected) {
            System.out.println(expected.getMessage());
        }
    }

    @Test
    public void testFindAllOrders() throws DatabaseOperationException {
        // Arrange
        OrderService orderService = ApplicationContext.getOrderService();
        orderService.saveOrUpdate(createTestOrder());

        // Act
        List<Order> orders = orderService.findAll();

        // Assert
        assertNotNull(orders,"Orders list should not be null");
        assertFalse(orders.isEmpty(),"Orders list should not be empty");
    }


    // Helper method to create a test order
    private Order createTestOrder() {
        return Order.builder()
                .description("Test order")
                .id(1L)
                .price(200.0)
                .time(LocalDateTime.now())
                .taskState(TaskState.STARTED)
                .rate(Rate.GOOD)
                .build();
    }
}