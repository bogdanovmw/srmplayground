package ru.chel.SRMPlayGround.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrdersDto {
    private Long id;
    @NotNull
    private Long driver;
    @NotNull
    private Long customer;
    @NotNull
    private Boolean status;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", driver=" + driver +
                ", customer=" + customer +
                ", status=" + status +
                '}';
    }
}
