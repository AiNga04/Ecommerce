package hcmute.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    private String address;
    private String phoneNumber;
    private String note;
    private int totalPrice;
    private int totalProduct;
    private int finalPrice;
    private String orderDay;
    private String shipDay;
    private String idPayMethod;
    private List<OrderItem> list;
    private int orderState;
    private int idBranch;
    private int fee;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        private int idMilkTea;
        private String size;
        private int quantity;
        private int price;
    }
}
