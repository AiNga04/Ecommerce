package hcmute.entity;

import hcmute.embeddedId.BranchMilkTeaId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch_milk_tea")
public class BranchMilkTea implements Serializable {

    @EmbeddedId
    private BranchMilkTeaId branchMilkTeaId;

    @Column(name = "remain_quantity")
    private int remainQuantity;

    @ManyToOne
    @JoinColumn(name = "id_branch", insertable = false, updatable = false)
    private BranchEntity branchByBranchMilkTea;

    @ManyToOne
    @JoinColumn(name = "id_milk_tea", insertable = false, updatable = false)
    private MilkTeaEntity milkTeaByBranchMilkTea;
}