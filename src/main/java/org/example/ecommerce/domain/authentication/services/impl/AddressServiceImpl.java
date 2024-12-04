package org.example.ecommerce.domain.authentication.services.impl;

import org.example.ecommerce.domain.authentication.entity.Address;
import org.example.ecommerce.domain.authentication.entity.User;
import org.example.ecommerce.domain.authentication.repositories.AddressRepository;
import org.example.ecommerce.domain.authentication.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;  // Inject repository để thao tác với database

    @Override
    public Address addAddress(User user, Address address) {
        // Set user cho địa chỉ, liên kết giữa Address và User
        address.setUser(user);
        return addressRepository.save(address);  // Lưu địa chỉ vào database
    }

    @Override
    public List<Address> getAddressesByUser(User user) {
        return addressRepository.findByUserId(user.getId());  // Lấy tất cả địa chỉ của người dùng
    }

    @Override
    public Address getDefaultAddress(User user) {
        // Lấy địa chỉ mặc định của người dùng, nếu có
        return addressRepository.findByUserAndIsDefaultTrue(user)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No default address found"));
    }
}
