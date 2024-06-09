package logic.service;

import base.service.BaseService;
import domain.SubService;

public interface SubServiceService extends BaseService<SubService, Long> {
    SubService create(SubService subService);

    SubService update(SubService subService);
}