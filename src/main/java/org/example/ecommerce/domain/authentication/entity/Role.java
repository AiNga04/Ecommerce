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
@Table(name = "tbl_role")
public class Role extends BaseEntity<Integer> implements Serializable {
    @Column(name = "role_name", nullable = false, unique = true, length = 50)
    private String roleName; // Tên của role, ví dụ: "USER", "ADMIN"

    @Column(name = "description", length = 255)
    private String description; // Mô tả về role này
}
