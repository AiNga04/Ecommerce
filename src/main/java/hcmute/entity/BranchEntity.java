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
@Table(name = "branch")
public class BranchEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_branch")
    private int idBranch;

    @Column(name = "name", columnDefinition = "varchar(1000)")
    private String name;

    @Column(name = "address_detail", columnDefinition = "varchar(1000)")
    private String addressDetail;

    @Column(name = "opentime", columnDefinition = "varchar(50)")
    private String opentime;

    @Column(name = "image", columnDefinition = "varchar(1000)")
    private String image;

    @Column(name = "description", columnDefinition = "varchar(1000)")
    private String description;

    @Column(name = "id_city", columnDefinition = "varchar(100)")
    private String idCity;

    @ManyToOne
    @JoinColumn(name = "id_city", insertable = false, updatable = false)
    private CityEntity cityByBranch;

    @OneToMany(mappedBy = "branchByUser")
    private Set<UserEntity> accounts;

    @OneToMany(mappedBy = "branchByOrder")
    private Set<Order> orders;

    @OneToMany(mappedBy = "branchByBranchMilkTea")
    private Set<BranchMilkTea> branchMilkTeas;
}