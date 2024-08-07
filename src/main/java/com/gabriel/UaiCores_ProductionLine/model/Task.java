package com.gabriel.UaiCores_ProductionLine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column (name = "task_id")
    private Long id;

    @Column (name = "task_amount")
    @NotNull
    @NotEmpty
    private Integer amount;

    @Column (name = "task_name")
    @NotNull
    @NotEmpty
    private String name;

    @Column (name = "task_description")
    @NotNull
    @NotEmpty
    private String description;

    @Column (name = "task_verseColor")
    @NotNull
    @NotEmpty
    private String verseColor;

    @Column
    @NotNull
    @NotEmpty
    private String material;

    public Task() {}

    public Task(Long id, Integer amount, String name, String description, String verseColor, String material) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.verseColor = verseColor;
        this.material = material;
    }
}
