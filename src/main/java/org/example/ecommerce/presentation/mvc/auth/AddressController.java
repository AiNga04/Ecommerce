package org.example.ecommerce.presentation.mvc.auth;

import org.example.ecommerce.domain.authentication.dto.requests.AddressRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AddressResponse;
import org.example.ecommerce.domain.authentication.entity.Address;
import org.example.ecommerce.domain.authentication.entity.User;
import org.example.ecommerce.domain.authentication.mapper.AddressMapper;
import org.example.ecommerce.domain.authentication.repositories.AddressRepository;
import org.example.ecommerce.domain.authentication.repositories.UserRepository;
import org.example.ecommerce.domain.authentication.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository

    @Autowired
    private AddressRepository addressRepository;  // Inject AddressRepository

    // Tạo địa chỉ mới cho người dùng
    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.createAddress(addressRequest);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    // Cập nhật địa chỉ
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Integer id, @RequestBody AddressRequest addressRequest) {
        AddressResponse updatedAddress = addressService.updateAddress(id, addressRequest);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    // Xóa địa chỉ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Lấy tất cả địa chỉ của người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponse>> getAddressesByUserId(@PathVariable Integer userId) {
        List<AddressResponse> addressResponses = addressService.getAddressesByUserId(userId);
        if (!addressResponses.isEmpty()) {
            return new ResponseEntity<>(addressResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}/default")
    public ResponseEntity<AddressResponse> getDefaultAddress(@PathVariable Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Address> defaultAddresses = addressRepository.findByUserAndIsDefaultTrue(user);
        if (defaultAddresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Address defaultAddress = defaultAddresses.get(0); // Giả sử chỉ có 1 địa chỉ mặc định
        return new ResponseEntity<>(AddressMapper.toResponse(defaultAddress), HttpStatus.OK);
    }


    // Lấy địa chỉ theo ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Integer id) {
        AddressResponse addressResponse = addressService.getAddressById(id);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }
}
