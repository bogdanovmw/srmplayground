package ru.chel.SRMPlayGround.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chel.SRMPlayGround.model.ProductList;

import java.util.List;

@Repository
public interface ProductListRepo extends JpaRepository<ProductList, Long> {
    List<ProductList> findByOrdersId(Long id);
    ProductList findByOrdersIdAndProductId(Long idOrder, Long idProduct);
    List<ProductList> findByProductId(Long id);
}
