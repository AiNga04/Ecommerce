package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilkTeaTypeModel {
    private int idType;
    private String name;
    private MilkTeaCategoryModel milkTeaCategoryByMilkTeaType;
    private MilkTeaModel milkTeas;
    private int idCategory;
    private Boolean isEdit = false;

}
