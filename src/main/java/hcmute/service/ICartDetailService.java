package hcmute.service;

import hcmute.embeddedId.CartDetailId;
import hcmute.entity.CartDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {

    void addProductToCart(int idCart, int idMilkTea, String size);

    void addProductToFavorite(int idCart, int idMilkTea);

    List<CartDetailId> findMilkTeaByCartId(int idCart);

    List<CartDetailEntity> findByCartByCartDetailIdCart(int idCart);

    Optional<CartDetailEntity> findById(CartDetailId id);

    void deleteAll();

    long count();

    List<CartDetailEntity> findAll(Sort sort);

    Page<CartDetailEntity> findAll(Pageable pageable);

    List<CartDetailEntity> findAll();

    <S extends CartDetailEntity> S save(S entity);

    void delete(CartDetailEntity entity);

}
