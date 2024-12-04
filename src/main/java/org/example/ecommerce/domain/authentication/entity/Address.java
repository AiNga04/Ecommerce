package org.example.ecommerce.domain.authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;

@Entity
@Table(name = "tbl_address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address extends BaseEntity<Integer> implements Serializable {

    @Column(name = "unit_street", length = 255)
    private String unitStreet; // Ví dụ: "Apt 10B"

    @Column(name = "street_number", length = 50)
    private String streetNumber; // Ví dụ: "123"

    @Column(name = "address_line1", length = 255)
    private String addressLine1; // Ví dụ: "Main St"

    @Column(name = "address_line2", length = 255)
    private String addressLine2; // Ví dụ: "Near Park"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user; // Mối quan hệ giữa Address và User

    @Column(name = "is_default")
    private boolean isDefault; // Đánh dấu địa chỉ mặc định của người dùng
}

