package logic.repository;

import base.repository.BaseRepository;
import domain.Expert;

public interface ExpertRepository extends BaseRepository<Expert, Long> {
    boolean signIn(String phone, String password);
}