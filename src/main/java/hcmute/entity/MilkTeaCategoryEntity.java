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
@Table(name = "milk_tea_category")
public class MilkTeaCategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int idCategory;

    @Column(name = "name", columnDefinition = "varchar(100)")
    private String name;

    @OneToMany(mappedBy = "milkTeaCategoryByMilkTeaType")
    private Set<MilkTeaTypeEntity> milkTeaTypes;

}