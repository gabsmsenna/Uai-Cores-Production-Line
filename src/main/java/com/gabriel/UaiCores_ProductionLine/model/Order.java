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
    private Instant orderEntryDate;

    @NotNull
    private Instant orderDeliveryDate;

    private String orderStatus;

    @NotNull
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Task> tasks = new ArrayList<>();


}
