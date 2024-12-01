package org.example.ecommerce.domain.authentication.repositories;

import org.example.ecommerce.domain.authentication.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
