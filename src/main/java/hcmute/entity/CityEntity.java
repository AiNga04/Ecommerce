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
@Table(name = "city")
public class CityEntity implements Serializable {

    @Id
    @Column(name = "id_city", columnDefinition = "varchar(100)")
    private String idCity;

    @Column(name = "name", columnDefinition = "varchar(50)")
    private String name;

    @OneToMany(mappedBy = "cityByBranch")
    private Set<BranchEntity> branches;
}