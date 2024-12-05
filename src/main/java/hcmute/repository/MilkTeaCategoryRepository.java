package hcmute.repository;

import hcmute.entity.MilkTeaCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilkTeaCategoryRepository extends JpaRepository<MilkTeaCategoryEntity, Integer> {
    List<MilkTeaCategoryEntity> findAll();
}
