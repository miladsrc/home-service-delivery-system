package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Expert;
import logic.repository.ExpertRepository;
import logic.service.ExpertService;

public class ExpertServiceImpl extends BaseServiceImpl<Expert, Long, ExpertRepository>
        implements ExpertService {

    public ExpertServiceImpl(ExpertRepository repository) {
        super(repository);
    }
}