package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Order;
import logic.repository.OrderRepository;
import logic.service.OrderService;
import util.ApplicationContext;

import java.util.List;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }




    @Override
    public void gerListOfOrders() {
        List<Order> orders = ApplicationContext.getOrderService().findAll();

        orders.forEach(order -> System.out.println(order.getId() + " " + order.getClient()));
    }

}