package hcmute.service.impl;

import hcmute.entity.PayMethodEntity;
import hcmute.repository.PayMethodRepository;
import hcmute.service.IPayMethodService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PayMethodServiceImpl implements IPayMethodService {
    PayMethodRepository payMethodRepository;

    @Override
    public List<PayMethodEntity> findAll() {
        return payMethodRepository.findAll();
    }

    @Override
    public List<PayMethodEntity> findAllById(Iterable<String> ids) {
        return payMethodRepository.findAllById(ids);
    }

    @Override
    public Optional<PayMethodEntity> findById(String id) {
        return payMethodRepository.findById(id);
    }

    @Override
    public <S extends PayMethodEntity> S save(S entity) {
        return payMethodRepository.save(entity);
    }

}
