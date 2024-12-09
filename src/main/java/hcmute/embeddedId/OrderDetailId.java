package hcmute.embeddedId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailId implements Serializable {
    @Column(name = "id_order")
    private int idOrder;

    @Column(name = "id_milk_tea")
    private int idMilkTea;

    @Column(name = "size", columnDefinition = "nvarchar(50)")
    private String size;
}
