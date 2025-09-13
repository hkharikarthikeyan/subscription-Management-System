package com.lumen.subscription_management.service;

import com.lumen.subscription_management.model.UsersUsage;
import java.util.List;

public interface SubscriptionService {
    UsersUsage subscribe(Long userId, Long planId);
    UsersUsage upgrade(Long subscriptionId, Long newPlanId);
    UsersUsage downgrade(Long subscriptionId, Long newPlanId);
    UsersUsage cancel(Long subscriptionId);
    List<UsersUsage> getUserSubscriptions(Long userId);
}
