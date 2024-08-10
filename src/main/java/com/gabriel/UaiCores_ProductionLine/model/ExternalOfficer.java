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
    private Long id;

    @Column(name = "externalofficer_name", unique = true, nullable = false)
    private String name;

    @Column (name = "externalofficer_login", unique = true, nullable = false)
    private String login;

    @Column(name = "externalofficer_password", nullable = false)
    private String password;

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        // Implementar o hashing da senha, por exemplo, usando BCrypt
        return password; // Alterar para o resultado do hash
    }

}
