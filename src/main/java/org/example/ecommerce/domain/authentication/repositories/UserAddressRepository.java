package org.example.ecommerce.domain.authentication.repositories;


import org.example.ecommerce.domain.authentication.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
}
