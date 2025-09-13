package com.lumen.subscription_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users_usage")
public class UsersUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // subscription id / reference

    @Column(name = "subscription_id")
    private Long subscriptionRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDetails user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private PlanInfo plan;

    @Column(name = "data_used")
    private Double dataUsed;

    @Column(name = "user_status")
    private String userStatus; // ACTIVE, CANCELLED, EXPIRED, etc.

    // =======================
    // Getters and Setters
    // =======================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubscriptionRef() {
        return subscriptionRef;
    }

    public void setSubscriptionRef(Long subscriptionRef) {
        this.subscriptionRef = subscriptionRef;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public PlanInfo getPlan() {
        return plan;
    }

    public void setPlan(PlanInfo plan) {
        this.plan = plan;
    }

    public Double getDataUsed() {
        return dataUsed;
    }

    public void setDataUsed(Double dataUsed) {
        this.dataUsed = dataUsed;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
