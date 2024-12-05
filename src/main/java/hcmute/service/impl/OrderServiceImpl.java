package hcmute.service.impl;

import hcmute.entity.BranchEntity;
import hcmute.entity.Order;
import hcmute.repository.OrderRepository;
import hcmute.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OrderServiceImpl implements IOrderService {
    OrderRepository orderRepository;

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    public int count() {
        return (int) orderRepository.count();
    }

    @Override
    public List<Object[]> getRevenueByDay() {
        return orderRepository.getRevenueByDay();
    }

    @Override
    public List<Object[]> getRevenueByMonth() {
        return orderRepository.getRevenueByMonth();
    }

    @Override
    public List<Order> findAllOrdersByUserId(Integer userId) {
        return orderRepository.findAllOrdersByUserId(userId);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByBranchByOrder(BranchEntity branchEntity) {
        return orderRepository.findByBranchByOrder(branchEntity);
    }

}
