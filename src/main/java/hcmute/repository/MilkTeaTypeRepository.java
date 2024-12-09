package hcmute.repository;

import hcmute.entity.MilkTeaTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilkTeaTypeRepository extends JpaRepository<MilkTeaTypeEntity, Integer> {

    @Query("SELECT mt FROM MilkTeaTypeEntity mt WHERE mt.milkTeaCategoryByMilkTeaType.idCategory = :categoryId")
    List<MilkTeaTypeEntity> findAllByCategoryId(@Param("categoryId") int categoryId);

    long count();

    Page<MilkTeaTypeEntity> findByIdTypeContaining(int idType, Pageable pageable);

    Page<MilkTeaTypeEntity> findAll(Pageable pageable);
}