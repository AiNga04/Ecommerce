package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.entity.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findById(Integer id);

    Role save(Role entity);

    void delete(Integer id);

    RoleResponse createRole(RoleRequest roleRequest);

    RoleResponse updateRole(Integer roleId, RoleRequest roleRequest);

    void deleteRole(Integer roleId);
}
