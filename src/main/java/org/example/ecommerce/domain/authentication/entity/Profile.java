package org.example.ecommerce.domain.authentication.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_profile")

public class Profile extends BaseEntity<Integer> implements Serializable {
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "gender", length = 10)
    String gender;

    @Column(name = "interest", length = 255)
    String interest;

    @Lob
    @Column(name = "avatar")
    byte[] avatar;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
