package hcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int idOrder;

    @Column(name = "total_product")
    private int totalProduct;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "final_price")
    private int finalPrice;

    @Column(name = "order_day", columnDefinition = "datetime")
    private LocalDateTime orderDay;

    @Column(name = "order_state")
    private int orderState;

    @Column(name = "ship_day", columnDefinition = "datetime")
    private LocalDateTime shipDay;

    @Column(name = "fee")
    private int fee;

    @Column(name = "note", columnDefinition = "varchar(1000)")
    private String note;

    @Column(name = "address", columnDefinition = "varchar(1000)")
    private String address;

    @Column(name = "phone_number", columnDefinition = "varchar(50)")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "id_pay_method")
    private PayMethodEntity payMethodByOrder;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private BranchEntity branchByOrder;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserEntity customerByOrder;

    @OneToMany(mappedBy = "orderByOrderDetail")
    private Set<OrderDetailEntity> orderDetails;
}