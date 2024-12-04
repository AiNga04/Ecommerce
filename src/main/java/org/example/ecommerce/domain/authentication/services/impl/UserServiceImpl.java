package org.example.ecommerce.domain.authentication.services.impl;

import org.example.ecommerce.domain.authentication.dto.requests.UserRequest;
import org.example.ecommerce.domain.authentication.dto.responses.UserResponse;
import org.example.ecommerce.domain.authentication.entity.User;
import org.example.ecommerce.domain.authentication.entity.Role;
import org.example.ecommerce.domain.authentication.mapper.UserMapper;
import org.example.ecommerce.domain.authentication.repositories.UserRepository;
import org.example.ecommerce.domain.authentication.repositories.RoleRepository; // Inject RoleRepository
import org.example.ecommerce.domain.authentication.services.IUserService;
import org.example.ecommerce.domain.common.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Inject RoleRepository

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        // Lấy Role từ roleRepository dựa trên tên role
        Role role = roleRepository.findByRoleName(userRequest.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Map UserRequest thành User và gán Role cho User
        User user = UserMapper.toEntity(userRequest, role);
        User savedUser = save(user);
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Integer userId, UserRequest userRequest) {
        Optional<User> existingUser = findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFullname(userRequest.getFullname());
            user.setStatus(userRequest.isStatus());
            user.setType(userRequest.getType());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            user.setUsername(userRequest.getUsername());
            user.setPassword(PasswordUtil.encodePassword(userRequest.getPassword()));

            // Kiểm tra nếu role có thay đổi
            if (userRequest.getRole() != null) {
                // Sử dụng RoleRepository để lấy Role từ database
                Role newRole = roleRepository.findByRoleName(userRequest.getRole())
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                user.setRole(newRole); // Cập nhật Role mới
            }

            User updatedUser = save(user);
            return UserMapper.toResponse(updatedUser);
        }
        return null;
    }


    @Override
    public void deleteUser(Integer userId) {
        delete(userId);
    }
}
