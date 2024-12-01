package org.example.ecommerce.domain.authentication.services;

import org.example.ecommerce.domain.authentication.dto.requests.UserRequest;
import org.example.ecommerce.domain.authentication.dto.responses.UserResponse;
import org.example.ecommerce.domain.authentication.entity.User;

import java.util.Optional;


public interface IUserService {
    Optional<User> findById(Integer id);

    User save(User entity);

    void delete(Integer id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Integer userId, UserRequest userRequest);

    void deleteUser(Integer userId);
}