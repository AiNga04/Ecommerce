package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.example.ecommerce.contract.shared.model.BaseEntity;
import org.example.ecommerce.domain.product.util.Status;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_brand")
public class Brand extends BaseEntity<Integer> implements Serializable {
    private String name;

    private String image;

    private String description;

    private String information;

    private String origin;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean adminOnly;
}
