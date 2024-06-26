package com.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shorten_users")
public class ShortenUserEntity {

    @Id
    private String id;
    private String userCode;
    private String username;
    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private int role;
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;
}
