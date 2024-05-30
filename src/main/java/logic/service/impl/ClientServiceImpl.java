package logic.service.impl;

import base.service.BaseServiceImpl;
import domain.Client;
import logic.repository.ClientRepository;
import logic.service.ClientService;

public class ClientServiceImpl extends BaseServiceImpl<Client, Long, ClientRepository>
implements ClientService {
    public ClientServiceImpl(ClientRepository repository) {
        super(repository);
    }
}
