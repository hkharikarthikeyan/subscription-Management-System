package com.lumen.subscription_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(name = "plan_name")
    private String planName;

    private Integer quota; // GB

    private Double price;

    @Column(name = "plan_status")
    private String planStatus; // e.g., ACTIVE, INACTIVE

    private Double discount; // percent or absolute (team decision)
}

