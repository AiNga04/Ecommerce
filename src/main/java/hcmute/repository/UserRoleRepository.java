package hcmute.repository;

import hcmute.entity.UserEntity;
import hcmute.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

    @Query("SELECT DISTINCT a FROM UserRoleEntity a WHERE a.user IN ?1")
    List<UserRoleEntity> authoritiesOf(List<UserEntity> accounts);

    @Query("SELECT COUNT(a) FROM UserRoleEntity a WHERE a.role.id = 'USER'")
    int countUser();

    List<UserRoleEntity> findByRoleId(String id);
}