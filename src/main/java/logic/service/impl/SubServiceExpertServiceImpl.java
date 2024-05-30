package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.SubService_Expert;
import logic.repository.SubServiceExpertRepository;
import logic.service.SubServiceExpertService;

public class SubServiceExpertServiceImpl extends BaseServiceImpl<SubService_Expert, Long, SubServiceExpertRepository>
        implements SubServiceExpertService {

    public SubServiceExpertServiceImpl(SubServiceExpertRepository repository) {
        super(repository);
    }
}