package com.gabriel.UaiCores_ProductionLine.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Table(name = "external_officer")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExternalOfficer implements Serializable {

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
}
