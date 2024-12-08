package hcmute.repository;

import hcmute.entity.UserEntity;
import hcmute.model.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT DISTINCT ur.user FROM UserRoleEntity ur WHERE ur.role.id IN ('ADMIN', 'USER', 'MANAGER')")
    List<UserEntity> getAdministrators();

    @Query("SELECT u FROM UserEntity u WHERE u.reset_pwd_token = ?1")
    UserEntity findByResetPasswordToken(String token);

    @Query("SELECT o FROM UserEntity o WHERE o.verify_code = ?1")
    UserEntity findByVerifyCode(String code);

    boolean existsUserByEmail(String email);

    boolean existsUserById(Integer id);

    @Modifying
    @Query("DELETE FROM UserEntity WHERE username = ?1")
    void deleteByUsername(String username);

    @Modifying
    @Query("UPDATE UserEntity u SET u.provider = ?2 WHERE u.email = ?1")
    void updateAuthenticationTypeOAuth(String email, AuthProvider provider);

    @Modifying
    @Query("UPDATE UserEntity u SET u.provider = ?2 WHERE u.username = ?1")
    void updateAuthenticationTypeDB(String username, AuthProvider provider);
}