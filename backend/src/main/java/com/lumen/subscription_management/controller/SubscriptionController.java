package com.lumen.subscription_management.controller;

import com.lumen.subscription_management.model.UsersUsage;
import com.lumen.subscription_management.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public UsersUsage subscribe(@RequestParam Long userId, @RequestParam Long planId) {
        return subscriptionService.subscribe(userId, planId);
    }

    @PostMapping("/upgrade")
    public UsersUsage upgrade(@RequestParam Long subscriptionId, @RequestParam Long newPlanId) {
        return subscriptionService.upgrade(subscriptionId, newPlanId);
    }

    @PostMapping("/downgrade")
    public UsersUsage downgrade(@RequestParam Long subscriptionId, @RequestParam Long newPlanId) {
        return subscriptionService.downgrade(subscriptionId, newPlanId);
    }

    @PostMapping("/cancel")
    public UsersUsage cancel(@RequestParam Long subscriptionId) {
        return subscriptionService.cancel(subscriptionId);
    }

    @GetMapping("/user")
    public List<UsersUsage> getUserSubscriptions(@RequestParam Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }
}
