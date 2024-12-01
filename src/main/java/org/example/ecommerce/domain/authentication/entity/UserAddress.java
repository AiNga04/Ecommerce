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
@Table(name = "tbl_user_address")
public class UserAddress extends BaseEntity<Integer> implements Serializable {
    @Column(name = "address_id", nullable = false)
    Long addressId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User user;
}
