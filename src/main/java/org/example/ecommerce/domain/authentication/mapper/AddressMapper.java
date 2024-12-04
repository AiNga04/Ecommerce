package org.example.ecommerce.domain.authentication.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.AddressRequest;
import org.example.ecommerce.domain.authentication.dto.responses.AddressResponse;
import org.example.ecommerce.domain.authentication.entity.Address;
import org.example.ecommerce.domain.authentication.entity.User;

public class AddressMapper {

    // AddressRequest -> Address Entity
    public static Address toEntity(AddressRequest addressRequest, User user) {
        return Address.builder()
                .unitStreet(addressRequest.getUnitStreet())
                .streetNumber(addressRequest.getStreetNumber())
                .addressLine1(addressRequest.getAddressLine1())
                .addressLine2(addressRequest.getAddressLine2())
                .user(user)
                .build();
    }

    // Address Entity -> AddressResponse
    public static AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .unitStreet(address.getUnitStreet())
                .streetNumber(address.getStreetNumber())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .build();
    }
}
