package org.example.ecommerce.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.example.ecommerce.contract.shared.model.BaseEntity;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_review")
public class Review extends BaseEntity<Integer> implements Serializable {
}
