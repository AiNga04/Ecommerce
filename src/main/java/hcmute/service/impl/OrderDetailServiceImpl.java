package hcmute.service.impl;

import hcmute.embeddedId.OrderDetailId;
import hcmute.entity.OrderDetailEntity;
import hcmute.repository.OrderDetailRepository;
import hcmute.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OrderDetailServiceImpl implements IOrderDetailService {

    OrderDetailRepository orderDetailRepository;

    @Override
    public <S extends OrderDetailEntity> S save(S entity) {
        return orderDetailRepository.save(entity);
    }

    @Override
    public <S extends OrderDetailEntity> List<S> saveAll(Iterable<S> entities) {
        return orderDetailRepository.saveAll(entities);
    }

    @Override
    public OrderDetailEntity getById(OrderDetailId id) {
        return orderDetailRepository.getById(id);
    }

    @Override
    public List<Object[]> getQuantityByMilkTeaType() {
        return orderDetailRepository.getQuantityByMilkTeaType();
    }

}
