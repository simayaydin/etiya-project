package com.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_logs")
@Getter
@Setter
@NoArgsConstructor

public class UserLog {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "action_type") // LOGIN, LOGOUT, PASSWORD_RESET vs.
    private String actionType;

    @Column(name = "action_time")
    private LocalDateTime actionTime;

    public UserLog(String username, String actionType, LocalDateTime actionTime) {
        this.username = username;
        this.actionType = actionType;
        this.actionTime = actionTime;
    }

}
