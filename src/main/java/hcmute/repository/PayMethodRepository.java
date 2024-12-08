package hcmute.repository;

import hcmute.entity.PayMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethodEntity, String> {

}
