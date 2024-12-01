package org.example.ecommerce.domain.authentication.dto.requests;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressRequest implements Serializable {
    private Long addressId;
}
