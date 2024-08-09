package com.gabriel.UaiCores_ProductionLine.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "order_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "order_id")
    private Long id;

    @NotNull
    @NotEmpty
    private Instant orderEntryDate;

    @NotNull
    @NotEmpty
    private Instant orderDeliveryDate;

    private String orderStatus;

    @NotNull
    @NotEmpty
    @ManyToOne
    public Client client;

    @OneToMany
    @NotNull
    @NotEmpty
    public List<Task> tasks = new ArrayList<>();


}
