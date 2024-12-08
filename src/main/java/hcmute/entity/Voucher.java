package hcmute.entity;

import hcmute.model.enums.Status;
import hcmute.model.enums.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voucher extends BaseEntity<Long> {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String code;

    private String name;

    private String description;

    private Long discount;

    private Long minPrice;

    private Long maxPrice;

    private Long maxDiscount;

    private Long quantity;

    private Date startDate;

    private Date endDate;

    private Long quantityUsed;

    private VoucherType type;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "voucher_order", joinColumns = @JoinColumn(name = "voucher_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private List<Order> orders;

    // voucher of shop
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private MilkTeaCategoryEntity category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "voucher_product",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "id_milk_tea")
    )
    private List<MilkTeaEntity> specificProducts;

    // New fields for applicability
    private boolean applyToAll = true;
}