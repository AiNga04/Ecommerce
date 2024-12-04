package org.example.ecommerce.domain.authentication.mapper;

import org.example.ecommerce.domain.authentication.dto.requests.UserRequest;
import org.example.ecommerce.domain.authentication.dto.responses.UserResponse;
import org.example.ecommerce.domain.authentication.entity.User;
import org.example.ecommerce.domain.authentication.entity.Role;
import org.example.ecommerce.domain.authentication.repositories.RoleRepository;
import org.example.ecommerce.domain.common.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    private static RoleRepository roleRepository; // Inject RoleRepository

    // UserRequest -> Entity, thêm tham số Role
    public static User toEntity(UserRequest userRequest, Role userRole) {
        return User.builder()
                .fullname(userRequest.getFullname())
                .status(userRequest.isStatus())
                .type(userRequest.getType())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .username(userRequest.getUsername())
                .password(PasswordUtil.encodePassword(userRequest.getPassword()))
                .role(userRole) // Gán userRole vào User
                .build();
    }

    // Entity -> UserResponse
    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .status(user.isStatus())
                .type(user.getType())
                .email(user.getEmail())
                .phone(user.getPhone())
                .username(user.getUsername())
                .role(user.getRole().getRoleName()) // Gán tên Role vào response
                .build();
    }
}
