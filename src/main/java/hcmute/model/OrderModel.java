package hcmute.model;

import hcmute.entity.BranchEntity;
import hcmute.entity.PayMethodEntity;
import hcmute.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private int idOrder;
    private int totalProduct;
    private int totalPrice;
    private int finalPrice;
    private LocalDateTime orderDay;
    private int orderState;
    private LocalDateTime shipDay;
    private String note;
    private String address;
    private String phoneNumber;
    private int fee;
    private PayMethodEntity payMethodByOrder;
    private UserEntity customerByOrder;
    private BranchEntity branchByOrder;
    private Boolean isEdit;
}
