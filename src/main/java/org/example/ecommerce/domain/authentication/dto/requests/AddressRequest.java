package org.example.ecommerce.domain.authentication.dto.requests;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest implements Serializable {
    private Integer userId;
    private String unitStreet;
    private String streetNumber;
    private String addressLine1;
    private String addressLine2;
    private boolean isDefault;
}
