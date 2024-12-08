package hcmute.service;

import hcmute.entity.PayMethodEntity;

import java.util.List;
import java.util.Optional;

public interface IPayMethodService {

    List<PayMethodEntity> findAll();

    List<PayMethodEntity> findAllById(Iterable<String> ids);

    Optional<PayMethodEntity> findById(String id);

    <S extends PayMethodEntity> S save(S entity);

}
