package org.beeapp.backend.repository;

import org.beeapp.backend.api.internal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findUserByEmail(String email);
    boolean existsByEmail(String email);
    Users findUsersById(Integer userId);
}