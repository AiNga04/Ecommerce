package hcmute.repository;

import hcmute.entity.MilkTeaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilkTeaRepository extends JpaRepository<MilkTeaEntity, Integer> {

    @Query("SELECT mt FROM MilkTeaEntity mt WHERE mt.milkTeaTypeByMilkTea.idType = :typeId")
    List<MilkTeaEntity> findAllByTypeId(@Param("typeId") int typeId);

    Optional<MilkTeaEntity> findByIdMilkTea(int id);

    @Query(
            value = "SELECT mt.* FROM milk_tea mt " +
                    "JOIN (SELECT id_milk_tea FROM order_detail GROUP BY id_milk_tea ORDER BY SUM(quantity) DESC LIMIT 5) od " +
                    "ON mt.id_milk_tea = od.id_milk_tea",
            nativeQuery = true
    )
    List<MilkTeaEntity> findFiveProductOutstanding();

    List<MilkTeaEntity> findAll();

    Page<MilkTeaEntity> findAll(Pageable pageable);

    @Query("SELECT m FROM MilkTeaEntity m WHERE m.milkTeaTypeByMilkTea.idType = :typeId AND m.idMilkTea <> :milkTeaId")
    List<MilkTeaEntity> findRelevantProducts(@Param("typeId") int typeId, @Param("milkTeaId") int milkTeaId);

    long count();

    @Query(
            value = "SELECT * FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci",
            nativeQuery = true
    )
    List<MilkTeaEntity> findByNameContaining(@Param("name") String name);

    @Query(
            value = "SELECT * FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci " +
                    "ORDER BY cost ASC",
            nativeQuery = true
    )
    List<MilkTeaEntity> findByNameContainingAndSortAscendingByCost(@Param("name") String name);

    @Query(
            value = "SELECT * FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci " +
                    "ORDER BY cost DESC",
            nativeQuery = true
    )
    List<MilkTeaEntity> findByNameContainingAndSortDescendingByCost(@Param("name") String name);

    @Query(
            value = "SELECT * FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci",
            nativeQuery = true
    )
    Page<MilkTeaEntity> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query(
            value = "SELECT * FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci " +
                    "ORDER BY cost ASC",
            nativeQuery = true
    )
    Page<MilkTeaEntity> findByNameContainingAndSortAscendingByCost(@Param("name") String name, Pageable pageable);

    @Query(
            value = "SELECT * FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci " +
                    "ORDER BY cost DESC",
            nativeQuery = true
    )
    Page<MilkTeaEntity> findByNameContainingAndSortDescendingByCost(@Param("name") String name, Pageable pageable);

    @Query("SELECT COUNT(mt) FROM MilkTeaEntity mt WHERE mt.milkTeaTypeByMilkTea.idType = :typeId")
    int countByTypeId(@Param("typeId") int typeId);

    @Query(
            value = "SELECT COUNT(*) FROM milk_tea WHERE " +
                    "LOWER(name) LIKE CONCAT('%', :name, '%') COLLATE utf8mb4_general_ci",
            nativeQuery = true
    )
    int countByNameContaining(@Param("name") String name);

    @Query("SELECT mt FROM MilkTeaEntity mt WHERE mt.milkTeaTypeByMilkTea.idType = :idType")
    Page<MilkTeaEntity> findAllByTypeId(@Param("idType") int idType, Pageable pageable);
}