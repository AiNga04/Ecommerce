package hcmute.service;

import hcmute.embeddedId.OrderDetailId;
import hcmute.entity.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailService {

    <S extends OrderDetailEntity> List<S> saveAll(Iterable<S> entities);

    <S extends OrderDetailEntity> S save(S entity);

    OrderDetailEntity getById(OrderDetailId id);

    List<Object[]> getQuantityByMilkTeaType();

}
