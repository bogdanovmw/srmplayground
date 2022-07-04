package ru.chel.SRMPlayGround.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "driver")
@Data
public class Driver{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "drivers")
//    @JsonIgnoreProperties("listProd")
    @JsonIgnore
    private List<Orders> orders;

    @OneToMany(mappedBy = "driver")
    @JsonIgnoreProperties("driver")
    private List<Customer> customers;
}
