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
@Table(name = "milk_tea_type")
public class MilkTeaTypeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private int idType;

    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private MilkTeaCategoryEntity milkTeaCategoryByMilkTeaType;

    @OneToMany(mappedBy = "milkTeaTypeByMilkTea")
    private Set<MilkTeaEntity> milkTeas;
}