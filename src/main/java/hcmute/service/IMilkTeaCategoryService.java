package hcmute.service;

import hcmute.entity.MilkTeaCategoryEntity;

import java.util.List;
import java.util.Optional;

public interface IMilkTeaCategoryService {

    List<MilkTeaCategoryEntity> findAll();

    Optional<MilkTeaCategoryEntity> findById(int id);

    <S extends MilkTeaCategoryEntity> S save(S entity);
}

	
