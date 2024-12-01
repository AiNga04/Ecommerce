package org.example.ecommerce.domain.authentication.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.UserAddressRequest;
import org.example.ecommerce.domain.authentication.dto.responses.UserAddressResponse;
import org.example.ecommerce.domain.authentication.entity.UserAddress;

public class UserAddressMapper {
    // UserAddressRequest -> Entity
    public static UserAddress toEntity(UserAddressRequest userAddressRequest) {
        return UserAddress.builder()
                .addressId(userAddressRequest.getAddressId())
                .build();
    }

    // Entity -> UserAddressResponse
    public static UserAddressResponse toResponse(UserAddress userAddress) {
        return UserAddressResponse.builder()
                .id(userAddress.getId())
                .addressId(userAddress.getAddressId())
                .build();
    }
}
