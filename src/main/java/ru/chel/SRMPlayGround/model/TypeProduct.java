package ru.chel.SRMPlayGround.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "type_product")
@Data
public class TypeProduct{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "typeProduct", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("typeProduct")
    List<Product> products;
}
