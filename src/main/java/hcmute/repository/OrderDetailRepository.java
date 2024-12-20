package hcmute.repository;

import hcmute.embeddedId.OrderDetailId;
import hcmute.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderDetailId> {

    @Query(value = "SELECT * FROM order_detail WHERE id_milk_tea = :milkTeaID", nativeQuery = true)
    List<OrderDetailEntity> findOrderDetailsByIDMilkTea(@Param("milkTeaID") int milkTeaID);

    // Statistics quantity of each milk tea type in this month
    @Query(
            value = "SELECT mt.name, SUM(od.quantity) " +
                    "FROM order_detail od " +
                    "JOIN milk_tea mt ON od.id_milk_tea = mt.id_milk_tea " +
                    "JOIN user_order o ON od.id_order = o.id_order " +
                    "WHERE MONTH(o.order_day) = MONTH(CURRENT_DATE()) AND YEAR(o.order_day) = YEAR(CURRENT_DATE()) " +
                    "GROUP BY mt.id_milk_tea, mt.name",
            nativeQuery = true
    )
    List<Object[]> getQuantityByMilkTeaType();
}