package hcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class CartEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private int idCart;

    @Column(name = "total_product")
    private int totalProduct;

    @Column(name = "total_price")
    private int totalPrice;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserEntity customerByCart;

    @OneToMany(mappedBy = "cartByCartDetail")
    private Set<CartDetailEntity> cartDetails;
}
