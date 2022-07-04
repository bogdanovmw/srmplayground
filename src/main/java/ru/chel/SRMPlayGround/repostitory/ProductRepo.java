package ru.chel.SRMPlayGround.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chel.SRMPlayGround.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
