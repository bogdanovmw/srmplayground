package ru.chel.SRMPlayGround.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Orders extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
//    @JsonIgnoreProperties("orders")
    @JsonIgnore
    private Driver drivers;

    @ManyToOne
    @JoinColumn(name = "customer_id")
//    @JsonIgnoreProperties("orders")
    @JsonIgnore
    private Customer customers;

    @OneToMany(mappedBy = "orders")
    @JsonIgnoreProperties("orders")
    private List<ProductList> listProd;

    private Boolean status;
}
