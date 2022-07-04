package ru.chel.SRMPlayGround.dto.orders;

import lombok.Data;
import lombok.ToString;
import ru.chel.SRMPlayGround.model.Product;

@Data
@ToString
public class ProductListDtoResponse {
    private Long id;
    private Product product;
    private String count;
    private String type;

    public String getProduct() {
        return product.getName();
    }
}
