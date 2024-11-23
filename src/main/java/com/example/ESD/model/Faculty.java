package com.example.ESD.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "faculty")
public class Faculty {
    @Id
    private int fid;
    private String username;
    private String password;
}
