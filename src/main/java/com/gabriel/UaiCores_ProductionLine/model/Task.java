package com.gabriel.UaiCores_ProductionLine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column (name = "task_id")
    private Long id;

    @Column (name = "task_amount")
    @NotNull
    @Min(1)
    private Integer amount;

    @Column (name = "task_name")
    @NotNull
    @NotEmpty
    private String name;

    @Column (name = "task_description")
    @NotNull
    @NotEmpty
    private String description;

    @Column (name = "task_verse_color")
    @NotNull
    @NotEmpty
    private String verseColor;

    @Column
    @NotNull
    @NotEmpty
    private String material;

    @ManyToOne
    @JoinColumn (name = "order_id")
    @JsonIgnore
    private Order order;
}
