package hcmute.entity;

import hcmute.embeddedId.CartDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_detail")
public class CartDetailEntity implements Serializable {

    @EmbeddedId
    private CartDetailId idCartDetail;

    @ManyToOne
    @JoinColumn(name = "id_cart", insertable = false, updatable = false)
    private CartEntity cartByCartDetail;

    @ManyToOne
    @JoinColumn(name = "id_milk_tea", insertable = false, updatable = false)
    private MilkTeaEntity milkTeaByCartDetail;
}