package org.example.ecommerce.domain.authentication.repositories;

import org.example.ecommerce.domain.authentication.entity.Address;
import org.example.ecommerce.domain.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByUserId(Integer userId); // Lấy tất cả địa chỉ của người dùng
    List<Address> findByUserAndIsDefaultTrue(User user); // Lấy địa chỉ mặc định của người dùng
}
