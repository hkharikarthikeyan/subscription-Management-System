package com.lumen.subscription_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @Column(name = "phone_no")
    private String phoneNo;

    private String email;

    /**
     * optional: store role ("USER" or "ADMIN")
     */
    private String role;
}
