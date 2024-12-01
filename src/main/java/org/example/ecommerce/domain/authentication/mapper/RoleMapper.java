package org.example.ecommerce.domain.authentication.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.entity.Role;

public class RoleMapper {

    // RoleRequest -> Entity
    public static Role toEntity(RoleRequest roleRequest) {
        return Role.builder()
                .name(roleRequest.getName())
                .description(roleRequest.getDescription())
                .build();
    }

    // Entity -> RoleResponse
    public static RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }
}
