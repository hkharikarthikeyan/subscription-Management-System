package com.lumen.subscription_management.repository;

import com.lumen.subscription_management.model.PlanInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanInfo, Long> {

}

