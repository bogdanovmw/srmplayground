package ru.chel.SRMPlayGround.dto.productList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductListDto {

    @JsonProperty("product")
    @NotNull
    private Long productId;

    @JsonProperty("order")
    @NotNull
    private Long orderId;

    @NotNull
    private double count;

    @NotNull
    private String type;

    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                ", orderId=" + orderId +
                ", count=" + count +
                ", type=" + type +
                '}';
    }
}

