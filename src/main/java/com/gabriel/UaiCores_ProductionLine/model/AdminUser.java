package com.gabriel.UaiCores_ProductionLine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "admin_user")
public class AdminUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column (unique = true, nullable = false)
    private String name;

    @Column (nullable = false)
    private String password;

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        return password;
    }
}
