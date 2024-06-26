package logic.repository;

import base.repository.BaseRepository;
import domain.Client;

public interface ClientRepository extends BaseRepository<Client, Long> {
    boolean signIn(String phone, String password);
}
