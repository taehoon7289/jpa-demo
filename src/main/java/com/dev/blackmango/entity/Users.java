package com.dev.blackmango.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;
    @Column(length = 50, nullable = false)
    private String id;
    @Column(length = 256, nullable = false)
    private String password;

    @Builder
    public Users(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
