package org.example.ecommerce.domain.authentication.dto.responses;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse implements Serializable {
    private Integer id;
    private String unitStreet;
    private String streetNumber;
    private String addressLine1;
    private String addressLine2;
}
