package com.gabriel.UaiCores_ProductionLine.repository;

import com.gabriel.UaiCores_ProductionLine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String userName);

}
