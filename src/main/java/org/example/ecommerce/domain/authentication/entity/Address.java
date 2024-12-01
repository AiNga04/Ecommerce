package org.example.ecommerce.domain.authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_address")
public class Address extends BaseEntity<Integer> implements Serializable {
    @Column(name = "unit_street", length = 255)
    private String unitStreet;

    @Column(name = "street_number", length = 50)
    private String streetNumber;

    @Column(name = "address_line1", length = 255)
    private String addressLine1;

    @Column(name = "address_line2", length = 255)
    private String addressLine2;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private UserAddress userAddress;
}
