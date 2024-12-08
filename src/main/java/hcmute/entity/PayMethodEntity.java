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
@Table(name = "pay_method")
public class PayMethodEntity implements Serializable {

    @Id
    @Column(name = "id_pay_method", columnDefinition = "varchar(100)")
    private String idPayMethod;

    @Column(name = "name", columnDefinition = "nvarchar(100)")
    private String name;

    @OneToMany(mappedBy = "payMethodByOrder")
    private Set<Order> orders;
}
