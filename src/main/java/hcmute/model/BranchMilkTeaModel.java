package hcmute.model;

import hcmute.embeddedId.BranchMilkTeaId;
import hcmute.entity.BranchEntity;
import hcmute.entity.MilkTeaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchMilkTeaModel {
    private BranchMilkTeaId branchMilkTeaId;
    private BranchEntity branchByBranchMilkTea;
    private MilkTeaEntity milkTeaByBranchMilkTea;
    private int remainQuantity;
    private Boolean isEdit = false;
    private int idBranch;
    private int idMilkTea;
    private String size;
}
