package com.gabriel.UaiCores_ProductionLine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gabriel.UaiCores_ProductionLine.config.LocalDateDeserializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate orderEntryDate;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate orderDeliveryDate;

    @NotEmpty
    private String orderStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client client;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Task> tasks = new ArrayList<>();


}
