package hcmute.service.impl;

import hcmute.entity.CityEntity;
import hcmute.repository.CityRepository;
import hcmute.service.ICityService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CityServiceImpl implements ICityService {
    CityRepository cityRepository;

    @Override
    public List<CityEntity> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public <S extends CityEntity> S save(S entity) {
        return cityRepository.save(entity);
    }

    @Override
    public Optional<CityEntity> findById(String id) {
        return cityRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        cityRepository.deleteById(id);
    }

}
