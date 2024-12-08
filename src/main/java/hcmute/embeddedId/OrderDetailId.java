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

//    public int getIdOrder() {
//        return idOrder;
//    }
//
//    public void setIdOrder(int idOrder) {
//        this.idOrder = idOrder;
//    }
//
//    public int getIdMilkTea() {
//        return idMilkTea;
//    }
//
//    public void setIdMilkTea(int idMilkTea) {
//        this.idMilkTea = idMilkTea;
//    }
//
//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(idMilkTea, idOrder, size);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        OrderDetailId other = (OrderDetailId) obj;
//        return idMilkTea == other.idMilkTea && idOrder == other.idOrder && Objects.equals(size, other.size);
//    }
}
