package hcmute.entity;

import hcmute.embeddedId.OrderDetailId;
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
@Table(name = "order_detail")
public class OrderDetailEntity implements Serializable {

    @EmbeddedId
    private OrderDetailId idOrderDetail;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "curr_price")
    private int currPrice;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private Order orderByOrderDetail;

    @ManyToOne
    @JoinColumn(name = "id_milk_tea", insertable = false, updatable = false)
    private MilkTeaEntity milkTeaByOrderDetail;
}