package org.example.ecommerce.domain.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity<Integer> implements Serializable {
    @Column(name = "fullname", nullable = false, length = 255)
    String fullname;

    @Column(name = "status", nullable = false)
    boolean status;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    String email;

    @Column(name = "phone", nullable = false, length = 15)
    String phone;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "role", nullable = false)
    String role;
}
