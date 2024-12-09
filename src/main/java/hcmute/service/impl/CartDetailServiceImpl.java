package hcmute.service.impl;

import hcmute.embeddedId.CartDetailId;
import hcmute.entity.CartDetailEntity;
import hcmute.repository.CartDetailRepository;
import hcmute.service.ICartDetailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CartDetailServiceImpl implements ICartDetailService {

    CartDetailRepository cartDetailRepository;

    @Override
    public <S extends CartDetailEntity> S save(S entity) {
        return cartDetailRepository.save(entity);
    }

    @Override
    public List<CartDetailEntity> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Page<CartDetailEntity> findAll(Pageable pageable) {
        return cartDetailRepository.findAll(pageable);
    }

    @Override
    public List<CartDetailEntity> findAll(Sort sort) {
        return cartDetailRepository.findAll(sort);
    }

    @Override
    public void delete(CartDetailEntity entity) {
        cartDetailRepository.delete(entity);
    }

    @Override
    public long count() {
        return cartDetailRepository.count();
    }

    @Override
    public void deleteAll() {
        cartDetailRepository.deleteAll();
    }

    @Override
    public Optional<CartDetailEntity> findById(CartDetailId id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public List<CartDetailEntity> findByCartByCartDetailIdCart(int idCart) {
        return cartDetailRepository.findByCartByCartDetailIdCart(idCart);
    }

    @Override
    public List<CartDetailId> findMilkTeaByCartId(int idCart) {
        return cartDetailRepository.findMilkTeaByCartId(idCart);
    }

    @Override
    public void addProductToCart(int idCart, int idMilkTea, String size) {
        cartDetailRepository.addProductToCart(idCart, idMilkTea, size);
    }

    @Override
    public void addProductToFavorite(int idCart, int idMilkTea) {
        cartDetailRepository.addProductToFavorite(idCart, idMilkTea);
    }

}
