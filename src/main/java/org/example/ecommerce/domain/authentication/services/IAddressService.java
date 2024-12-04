package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.dto.requests.AddressRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AddressResponse;

import java.util.List;

public interface IAddressService {
    AddressResponse createAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(Integer id, AddressRequest addressRequest);
    void deleteAddress(Integer id);
    List<AddressResponse> getAddressesByUserId(Integer userId);
    AddressResponse getAddressById(Integer id);
}
