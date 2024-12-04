package org.example.ecommerce.domain.authentication.services.impl;

import org.example.ecommerce.domain.authentication.dto.requests.AddressRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AddressResponse;
import org.example.ecommerce.domain.authentication.entity.Address;
import org.example.ecommerce.domain.authentication.entity.User;
import org.example.ecommerce.domain.authentication.mapper.AddressMapper;
import org.example.ecommerce.domain.authentication.repositories.AddressRepository;
import org.example.ecommerce.domain.authentication.repositories.UserRepository;
import org.example.ecommerce.domain.authentication.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        User user = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra và cập nhật địa chỉ mặc định
        if (addressRequest.isDefault()) {
            // Tìm tất cả địa chỉ mặc định của người dùng
            List<Address> existingDefaultAddresses = addressRepository.findByUserAndIsDefaultTrue(user);

            // Nếu đã có địa chỉ mặc định, cần phải cập nhật các địa chỉ khác thành không mặc định
            if (!existingDefaultAddresses.isEmpty()) {
                existingDefaultAddresses.forEach(address -> address.setDefault(false));
                addressRepository.saveAll(existingDefaultAddresses); // Lưu lại các thay đổi
            }
        }

        // Tạo và lưu địa chỉ mới
        Address address = AddressMapper.toEntity(addressRequest, user);
        address.setDefault(addressRequest.isDefault());  // Gán giá trị mặc định từ request
        Address savedAddress = addressRepository.save(address);

        return AddressMapper.toResponse(savedAddress);
    }


    @Override
    public AddressResponse updateAddress(Integer id, AddressRequest addressRequest) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // Cập nhật địa chỉ
        address.setUnitStreet(addressRequest.getUnitStreet());
        address.setStreetNumber(addressRequest.getStreetNumber());
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());

        // Kiểm tra và cập nhật địa chỉ mặc định nếu cần
        if (addressRequest.isDefault() && !address.isDefault()) {
            List<Address> existingAddresses = addressRepository.findByUserId(address.getUser().getId());
            existingAddresses.forEach(a -> a.setDefault(false));
            address.setDefault(true);
        }

        Address updatedAddress = addressRepository.save(address);
        return AddressMapper.toResponse(updatedAddress);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressResponse> getAddressesByUserId(Integer userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream()
                .map(AddressMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getAddressById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return AddressMapper.toResponse(address);
    }
}
