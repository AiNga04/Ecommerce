package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayMethodModel {
    private String idPayMethod;
    private String name;
    private OrderModel orders;
    private Boolean isEdit = false;
}
