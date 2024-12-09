package hcmute.service;

import hcmute.embeddedId.BranchMilkTeaId;
import hcmute.entity.BranchMilkTea;

import java.util.List;
import java.util.Optional;

public interface IBranchMilkTeaService {

    Optional<Integer> findRemainQuantityByBranchIdAndMilkTeaId(int idBranch, int idMilkTea, String size);

    Optional<BranchMilkTea> findById(BranchMilkTeaId id);

    List<BranchMilkTea> findAll();

    <S extends BranchMilkTea> S save(S entity);

    void deleteAll();
}
