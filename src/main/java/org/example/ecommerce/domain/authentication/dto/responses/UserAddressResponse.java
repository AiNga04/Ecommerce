package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressResponse implements Serializable {
    private Integer id;
    private Long addressId;
}
