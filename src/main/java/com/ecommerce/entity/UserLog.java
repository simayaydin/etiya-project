package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    @Column(name = "action_type") // LOGIN, LOGOUT, PASSWORD_RESET vs.
    private String actionType;

    @Column(name = "action_time")
    private LocalDateTime actionTime;

    public UserLog(String username, String email, String actionType, LocalDateTime actionTime) {
        this.username = username;
        this.email = email;
        this.actionType = actionType;
        this.actionTime = actionTime;
    }
}
