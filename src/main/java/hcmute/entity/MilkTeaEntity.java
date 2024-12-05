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
@Table(name = "milk_tea")
public class MilkTeaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_milk_tea")
    private int idMilkTea;

    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "description", columnDefinition = "varchar(1000)")
    private String description;

    @Column(name = "image", columnDefinition = "varchar(1000)")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private MilkTeaTypeEntity milkTeaTypeByMilkTea;

    @OneToMany(mappedBy = "milkTeaByCartDetail")
    private Set<CartDetailEntity> cartDetails;

    @OneToMany(mappedBy = "milkTeaByOrderDetail")
    private Set<OrderDetailEntity> orderDetails;

    @OneToMany(mappedBy = "milkTeaByBranchMilkTea")
    private Set<BranchMilkTea> branchMilkTeas;
}