package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.ecommerce.contract.shared.model.BaseEntity;
import org.example.ecommerce.domain.product.util.Status;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product_variant")
public class ProductVariant extends BaseEntity<Integer> implements Serializable {
    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_variant_attribute",
            joinColumns =
            @JoinColumn(name = "product_variant_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "attribute_id", nullable = true)
    )
    private List<Attribute> attributes;
}
