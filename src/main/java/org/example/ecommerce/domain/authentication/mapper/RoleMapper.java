package org.example.ecommerce.domain.authentication.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.RoleRequest;
import org.example.ecommerce.domain.authentication.dto.responses.RoleResponse;
import org.example.ecommerce.domain.authentication.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    // RoleRequest -> Entity
    public static Role toEntity(RoleRequest roleRequest) {
        return Role.builder()
                .roleName(roleRequest.getName())
                .description(roleRequest.getDescription())
                .build();
    }

    // Entity -> RoleResponse
    public static RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    // Danh sách Entity -> Danh sách RoleResponse
    public static List<RoleResponse> toResponseList(List<Role> roles) {
        return roles.stream()
                .map(RoleMapper::toResponse)
                .collect(Collectors.toList());
    }
}
