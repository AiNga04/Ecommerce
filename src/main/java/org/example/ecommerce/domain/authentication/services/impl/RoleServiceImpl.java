package org.example.ecommerce.domain.authentication.services.impl;

import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.entity.Role;
import org.example.ecommerce.domain.authentication.mapper.RoleMapper;
import org.example.ecommerce.domain.authentication.repositories.RoleRepository;
import org.example.ecommerce.domain.authentication.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = RoleMapper.toEntity(roleRequest);
        Role savedRole = save(role);
        return RoleMapper.toResponse(savedRole);
    }

    @Override
    public RoleResponse updateRole(Integer roleId, RoleRequest roleRequest) {
        Optional<Role> existingRole = findById(roleId);
        if (existingRole.isPresent()) {
            Role role = existingRole.get();
            role.setName(roleRequest.getName());
            role.setDescription(roleRequest.getDescription());
            Role updatedRole = save(role);
            return RoleMapper.toResponse(updatedRole);
        }
        return null;
    }

    @Override
    public void deleteRole(Integer roleId) {
        delete(roleId);
    }
}
