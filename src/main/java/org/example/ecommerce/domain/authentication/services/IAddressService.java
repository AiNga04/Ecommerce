package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.entity.Address;
import org.example.ecommerce.domain.authentication.entity.User;

import java.util.List;

public interface IAddressService {
    Address addAddress(User user, Address address); // Thêm địa chỉ mới cho người dùng
    List<Address> getAddressesByUser(User user);    // Lấy danh sách địa chỉ của người dùng
    Address getDefaultAddress(User user);           // Lấy địa chỉ mặc định của người dùng
}

