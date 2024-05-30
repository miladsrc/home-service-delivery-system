package logic.repository.impl;

import base.repository.BaseRepositoryImpl;
import domain.Order;
import logic.repository.OrderRepository;
import org.hibernate.Session;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order, Long> implements OrderRepository {
    public OrderRepositoryImpl(Session session) {
        super(session);
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}