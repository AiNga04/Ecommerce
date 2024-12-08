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
public class MilkTeaCategoryModel {
    private int idCategory;
    private String name;
    private Set<MilkTeaTypeModel> milkTeaTypes;
    private Boolean isEdit = false;
}
