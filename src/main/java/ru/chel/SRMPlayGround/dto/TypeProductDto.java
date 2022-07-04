package ru.chel.SRMPlayGround.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TypeProductDto {
    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}
