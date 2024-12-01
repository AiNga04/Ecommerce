package org.example.ecommerce.domain.authentication.repositories;

import org.example.ecommerce.domain.authentication.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
