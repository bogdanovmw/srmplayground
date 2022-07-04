package ru.chel.SRMPlayGround.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerDto {
    @NotNull
    @Size(min = 2, max = 255)
    private String name;
    @NotNull
    private Long driver;
    @NotNull
    private Integer position;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", driver=" + driver +
                ", position=" + position +
                '}';
    }
}
