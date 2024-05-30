package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.SubService;
import logic.repository.SubServiceRepository;
import logic.service.SubServiceService;

public class SubServiceServiceImpl extends BaseServiceImpl<SubService, Long, SubServiceRepository>
        implements SubServiceService {

    public SubServiceServiceImpl(SubServiceRepository repository) {
        super(repository);
    }
}