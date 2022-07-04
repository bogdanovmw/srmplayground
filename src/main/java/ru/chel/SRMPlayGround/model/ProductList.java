package ru.chel.SRMPlayGround.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product_list")
@Data
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
//    @JsonIgnoreProperties("listProd")
    @JsonIgnore
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("listProd")
    private Product product;

    private double count;

    private String type;
}
