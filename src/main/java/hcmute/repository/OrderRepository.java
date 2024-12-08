package hcmute.repository;

import hcmute.entity.BranchEntity;
import hcmute.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Statistics for each day of the current month
    @Query(
            value = "SELECT CAST(order_day AS DATE) AS order_date, SUM(final_price) AS total_price " +
                    "FROM user_order " +
                    "WHERE MONTH(order_day) = MONTH(CURRENT_DATE()) AND YEAR(order_day) = YEAR(CURRENT_DATE()) " +
                    "GROUP BY CAST(order_day AS DATE)",
            nativeQuery = true
    )
    List<Object[]> getRevenueByDay();

    // Statistics for each month of the current year
    @Query(
            "SELECT MONTH(o.orderDay), SUM(o.finalPrice) FROM Order o " +
                    "WHERE YEAR(o.orderDay) = YEAR(CURRENT_DATE()) " +
                    "GROUP BY MONTH(o.orderDay)"
    )
    List<Object[]> getRevenueByMonth();

    @Query("SELECT o FROM Order o WHERE o.customerByOrder.id = :userId")
    List<Order> findAllOrdersByUserId(@Param("userId") Integer userId);

    List<Order> findByBranchByOrder(BranchEntity branchEntity);
}