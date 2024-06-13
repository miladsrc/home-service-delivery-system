package logic.service;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseService;
import domain.SubService_Expert;

public interface SubServiceExpertService extends BaseService<SubService_Expert, Long> {
    boolean addExpertToSubDuty() throws DatabaseOperationException, NotFoundException;

    boolean deleteExpertFromSubService() throws NotFoundException, DatabaseOperationException;
}