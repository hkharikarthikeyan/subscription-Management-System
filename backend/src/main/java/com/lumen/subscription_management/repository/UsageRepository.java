package com.lumen.subscription_management.repository;

import com.lumen.subscription_management.model.UsersUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageRepository extends JpaRepository<UsersUsage, Long> {
    List<UsersUsage> findByUserId(Long userId);
}
