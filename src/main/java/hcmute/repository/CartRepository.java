package hcmute.repository;

import hcmute.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    @Query("SELECT c FROM CartEntity c WHERE c.customerByCart.id = :userId")
    Optional<CartEntity> findCartsByUserId(@Param("userId") int userId);
}