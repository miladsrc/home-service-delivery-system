package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Service;
import logic.repository.ServiceRepository;
import logic.service.ServiceService;

public class ServiceServiceImpl extends BaseServiceImpl<Service, Long, ServiceRepository>
        implements ServiceService {

    public ServiceServiceImpl(ServiceRepository repository) {
        super(repository);
    }
}