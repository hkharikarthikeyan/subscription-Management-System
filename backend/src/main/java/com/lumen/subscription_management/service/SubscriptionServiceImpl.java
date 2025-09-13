package com.lumen.subscription_management.service;

import com.lumen.subscription_management.model.PlanInfo;
import com.lumen.subscription_management.model.UserDetails;
import com.lumen.subscription_management.model.UsersUsage;
import com.lumen.subscription_management.repository.PlanRepository;
import com.lumen.subscription_management.repository.UserRepository;
import com.lumen.subscription_management.repository.UsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UsageRepository usageRepo;
    private final UserRepository userRepo;
    private final PlanRepository planRepo;

    // Constructor
    public SubscriptionServiceImpl(UsageRepository usageRepo, UserRepository userRepo, PlanRepository planRepo) {
        this.usageRepo = usageRepo;
        this.userRepo = userRepo;
        this.planRepo = planRepo;
    }

    @Override
    public UsersUsage subscribe(Long userId, Long planId) {
        UserDetails user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        PlanInfo plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        UsersUsage usage = new UsersUsage();
        usage.setUser(user);
        usage.setPlan(plan);
        usage.setDataUsed(0.0);
        usage.setUserStatus("ACTIVE");

        // Save first to generate ID
        UsersUsage saved = usageRepo.save(usage);

        // Set subscriptionRef same as ID
        saved.setSubscriptionRef(saved.getId());
        // No need to call save again; it's inside @Transactional
        return saved;
    }

    @Override
    public UsersUsage upgrade(Long subscriptionId, Long newPlanId) {
        UsersUsage usage = usageRepo.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        PlanInfo plan = planRepo.findById(newPlanId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        usage.setPlan(plan);
        usage.setUserStatus("UPGRADED");
        return usage; // auto-saved at transaction commit
    }

    @Override
    public UsersUsage downgrade(Long subscriptionId, Long newPlanId) {
        UsersUsage usage = usageRepo.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        PlanInfo plan = planRepo.findById(newPlanId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        usage.setPlan(plan);
        usage.setUserStatus("DOWNGRADED");
        return usage;
    }

    @Override
    public UsersUsage cancel(Long subscriptionId) {
        UsersUsage usage = usageRepo.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        usage.setUserStatus("CANCELLED");
        return usage;
    }

    @Override
    public List<UsersUsage> getUserSubscriptions(Long userId) {
        // Better: define in repository:
        // List<UsersUsage> findByUserId(Long userId);
        return usageRepo.findByUserId(userId);
    }
}
