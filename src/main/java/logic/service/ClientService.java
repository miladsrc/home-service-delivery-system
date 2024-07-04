package logic.service;

import base.exception.DatabaseOperationException;
import base.exception.NotFoundException;
import base.service.BaseService;
import domain.Client;

public interface ClientService extends BaseService<Client, Long> {
    Client signUp(Client client);

    boolean signIn(String phone, String password);

    boolean changeClientPassword(String phone, String password, String oldPassword, String newPassword);

    boolean confirmOfferOfOrder(Long offerId) throws NotFoundException, DatabaseOperationException;
}
