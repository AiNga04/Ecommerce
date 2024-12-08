package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailModel {
    private String size;
    private CartModel cartByCartDetail;
    private MilkTeaModel milkTeaByCartDetail;
    private int id_cart;
    private int id_milk_tea;
    private Boolean isEdit = false;
}
