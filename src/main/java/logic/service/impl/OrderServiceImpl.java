package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Order;
import logic.repository.OrderRepository;
import logic.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }
}