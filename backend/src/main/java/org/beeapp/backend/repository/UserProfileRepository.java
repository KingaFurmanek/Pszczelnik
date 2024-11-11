package org.beeapp.backend.repository;

import org.beeapp.backend.api.internal.UserProfile;
import org.beeapp.backend.api.internal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}