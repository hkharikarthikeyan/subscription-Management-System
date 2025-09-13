package com.lumen.subscription_management.repository;

import com.lumen.subscription_management.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

}

