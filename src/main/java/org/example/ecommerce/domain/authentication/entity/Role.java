package org.example.ecommerce.domain.authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_role")
public class Role extends BaseEntity<Integer> implements Serializable {
    String name;
    String description;
}
