package ru.chel.SRMPlayGround.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@ToString
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonIgnore
    private TypeProduct typeProduct;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductList> listProd;
}
