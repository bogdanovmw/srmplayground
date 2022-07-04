package ru.chel.SRMPlayGround.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerPositionDto {
    @NotNull
    private Integer position;

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                '}';
    }
}
