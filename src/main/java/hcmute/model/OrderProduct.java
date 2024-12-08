package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private List<OrderItem> list;
    private String totalProduct;
    private int totalPrice;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {
        private String idMilkTea;
        private String quantity;
        private int price;
        private String size;
    }
}

