package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> findAll();

    void delete(String role);
}
