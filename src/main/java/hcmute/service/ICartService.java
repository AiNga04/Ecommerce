package hcmute.service;

import hcmute.entity.CartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    void deleteAll();

    void deleteAllById(Iterable<? extends Integer> ids);

    void delete(CartEntity entity);

    long count();

    Optional<CartEntity> findById(Integer id);

    List<CartEntity> findAllById(Iterable<Integer> ids);

    List<CartEntity> findAll(Sort sort);

    Page<CartEntity> findAll(Pageable pageable);

    List<CartEntity> findAll();

    <S extends CartEntity> S save(S entity);

    void deleteById(Integer id);

    Optional<CartEntity> findCartsByUserId(int userId);
}
