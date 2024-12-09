package hcmute.service;

import hcmute.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<RoleEntity> findAll();

    Optional<RoleEntity> findById(String id);
}
