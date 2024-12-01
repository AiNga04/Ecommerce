package org.example.ecommerce.domain.authentication.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.mapper.RoleMapper;
import org.example.ecommerce.domain.authentication.repositories.PermissionRepository;
import org.example.ecommerce.domain.authentication.repositories.RoleRepository;
import org.example.ecommerce.domain.authentication.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(
        level = PRIVATE,
        makeFinal = true
)
public class RoleService implements IRoleService {

    @Override
    public RoleResponse create(RoleRequest request) {
        return null;
    }

    @Override
    public List<RoleResponse> findAll() {
        return List.of();
    }

    @Override
    public void delete(String role) {

    }
}
