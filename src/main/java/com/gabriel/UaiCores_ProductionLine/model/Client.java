package com.gabriel.UaiCores_ProductionLine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client () {}

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
