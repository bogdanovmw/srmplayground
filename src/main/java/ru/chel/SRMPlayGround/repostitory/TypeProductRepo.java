package ru.chel.SRMPlayGround.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chel.SRMPlayGround.model.TypeProduct;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProduct, Long> {
    TypeProduct findByName(String name);
}
