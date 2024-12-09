package hcmute.model;

import hcmute.embeddedId.OrderDetailId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailModel {
    private OrderDetailId idOrderDetail;
    private int quantity;
    private int currPrice;
    private int size;
    private OrderModel orderByOrderDetail;
    private MilkTeaModel milkTeaByOrderDetail;
}
