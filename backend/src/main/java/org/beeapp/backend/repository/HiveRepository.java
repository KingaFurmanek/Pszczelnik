package org.beeapp.backend.repository;

import org.beeapp.backend.api.internal.Hive;
import org.beeapp.backend.api.internal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiveRepository extends JpaRepository<Hive, Integer> {
    List<Hive> findByUser(Users user);
}
