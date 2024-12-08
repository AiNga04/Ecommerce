package hcmute.service.impl;

import hcmute.entity.MilkTeaCategoryEntity;
import hcmute.repository.MilkTeaCategoryRepository;
import hcmute.service.IMilkTeaCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)

public class MilkTeaCategoryServiceImpl implements IMilkTeaCategoryService {
    MilkTeaCategoryRepository milkTeaCategoryRepository;

    @Override
    public List<MilkTeaCategoryEntity> findAll() {
        return milkTeaCategoryRepository.findAll();
    }

    @Override
    public Optional<MilkTeaCategoryEntity> findById(int id) {
        return milkTeaCategoryRepository.findById(id);
    }

    @Override
    public <S extends MilkTeaCategoryEntity> S save(S entity) {
        return milkTeaCategoryRepository.save(entity);
    }

}
