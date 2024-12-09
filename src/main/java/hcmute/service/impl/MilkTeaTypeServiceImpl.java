package hcmute.service.impl;

import hcmute.entity.MilkTeaTypeEntity;
import hcmute.repository.MilkTeaTypeRepository;
import hcmute.service.IMilkTeaTypeService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class MilkTeaTypeServiceImpl implements IMilkTeaTypeService {

    MilkTeaTypeRepository milkTeaTypeRepository;

    @Override
    public long count() {
        return milkTeaTypeRepository.count();
    }

    @Override
    public Page<MilkTeaTypeEntity> findAll(Pageable pageable) {
        return milkTeaTypeRepository.findAll(pageable);
    }

    @Override
    public Page<MilkTeaTypeEntity> findByidTypeContaining(int idType, Pageable pageable) {
        return milkTeaTypeRepository.findByIdTypeContaining(idType, pageable);
    }

    @Override
    public List<MilkTeaTypeEntity> findAllByCategoryId(int categoryId) {
        return milkTeaTypeRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public <S extends MilkTeaTypeEntity> S save(S entity) {
        return milkTeaTypeRepository.save(entity);
    }

    @Override
    public Optional<MilkTeaTypeEntity> findById(Integer id) {
        return milkTeaTypeRepository.findById(id);
    }

    @Override
    public List<MilkTeaTypeEntity> findAll() {
        return milkTeaTypeRepository.findAll();
    }

}
