package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_attribute")
public class Attribute extends BaseEntity<Integer> implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(mappedBy = "attributes", fetch = FetchType.EAGER)
    private List<ProductVariant> productVariants;

    public boolean isUsedInProductVariants() {
        return productVariants != null && !productVariants.isEmpty();
    }
}
