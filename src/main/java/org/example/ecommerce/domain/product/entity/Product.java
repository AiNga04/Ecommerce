package org.example.ecommerce.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.ecommerce.contract.shared.model.BaseEntity;
import org.example.ecommerce.domain.review.entity.Review;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
public class Product extends BaseEntity<Integer> implements Serializable {
    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = true)
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductVariant> productVariants;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;
}
