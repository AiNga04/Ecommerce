package hcmute.service;

import hcmute.entity.UserRoleEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRoleService {
    List<UserRoleEntity> findRolesOfAdministrators();

    List<UserRoleEntity> findAll();

    UserRoleEntity create(UserRoleEntity auth);

    void delete(Integer id);

    int countUser();

    List<UserRoleEntity> findByRoleId(String name);

    Optional<UserRoleEntity> findById(Integer id);

    <S extends UserRoleEntity> S save(S entity);
}
