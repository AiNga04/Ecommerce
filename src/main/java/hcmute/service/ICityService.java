package hcmute.service;

import hcmute.entity.CityEntity;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    List<CityEntity> findAll();

    <S extends CityEntity> S save(S entity);

    Optional<CityEntity> findById(String id);

    void deleteById(String id);

}
