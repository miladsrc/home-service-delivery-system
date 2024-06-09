package logic.service;

import base.service.BaseService;
import domain.Service;

public interface ServiceService extends BaseService<Service, Long> {
    Service create(Service service);

    Service update(Service service);
}