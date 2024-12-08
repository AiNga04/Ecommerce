package hcmute.service;

import hcmute.entity.BranchEntity;
import hcmute.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    <S extends Order> S save(S entity);

    List<Order> findAll();

    Order getById(Integer id);

    int count();

    List<Object[]> getRevenueByDay();

    List<Object[]> getRevenueByMonth();

    List<Order> findAllOrdersByUserId(Integer userId);

    Optional<Order> findById(Integer id);

    List<Order> findByBranchByOrder(BranchEntity branchEntity);
}
