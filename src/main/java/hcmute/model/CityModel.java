package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityModel {
    private String idCity;
    private String name;
    private BranchModel branches;
    private Boolean isEdit = false;
}
