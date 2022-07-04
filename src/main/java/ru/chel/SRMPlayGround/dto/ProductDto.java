package ru.chel.SRMPlayGround.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductDto {
    @NotNull
    @Size(min = 2, max = 255)
    String name;

    @NotNull
    Long parent;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
