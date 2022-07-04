package ru.chel.SRMPlayGround.dto.productList;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateProductListDto {
    @NotNull
    private double count;

    @NotNull
    private String type;

    @Override
    public String toString() {
        return "{" +
                "count=" + count +
                ", type=" + type +
                '}';
    }
}
