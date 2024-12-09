package hcmute.repository;

import hcmute.embeddedId.BranchMilkTeaId;
import hcmute.entity.BranchMilkTea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchMilkTeaRepository extends JpaRepository<BranchMilkTea, BranchMilkTeaId> {

    @Query("SELECT COALESCE(b.remainQuantity, 0) FROM BranchMilkTea b WHERE b.branchMilkTeaId.idBranch = :idBranch AND b.branchMilkTeaId.idMilkTea = :idMilkTea AND b.branchMilkTeaId.size = :size")
    Optional<Integer> findRemainQuantityByBranchIdAndMilkTeaId(
            @Param("idBranch") int idBranch, @Param("idMilkTea") int idMilkTea, @Param("size") String size
    );
}