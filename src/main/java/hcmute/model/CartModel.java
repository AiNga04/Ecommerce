package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
    private int idCart;
    private int totalProduct;
    private int totalPrice;
    private UserModel customerByCart;
    private Set<CartDetailModel> cartDetails;
    private Boolean isEdit = false;
}
