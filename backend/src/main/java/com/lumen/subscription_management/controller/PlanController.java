package com.lumen.subscription_management.controller;

import com.lumen.subscription_management.model.PlanInfo;
import com.lumen.subscription_management.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanRepository planRepo;

    @PostMapping
    public PlanInfo createPlan(@RequestBody PlanInfo plan) {
        return planRepo.save(plan);
    }

    @GetMapping("/{id}")
    public PlanInfo getPlan(@PathVariable Long id) {
        return planRepo.findById(id).orElseThrow(() -> new RuntimeException("Plan not found"));
    }
}
