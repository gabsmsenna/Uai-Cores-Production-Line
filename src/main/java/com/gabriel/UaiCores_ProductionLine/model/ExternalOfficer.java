package com.gabriel.UaiCores_ProductionLine.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "external_officer")
public class ExternalOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "externalofficer_id")
    private Integer id;

    @Column(name = "externalofficer_name", unique = true, nullable = false)
    private String name;

    @Column (name = "externalofficer_login", unique = true, nullable = false)
    private String login;

    @Column (name = "externalofficer_password")
    private String password;

    public ExternalOfficer(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public ExternalOfficer() {

    }
}
