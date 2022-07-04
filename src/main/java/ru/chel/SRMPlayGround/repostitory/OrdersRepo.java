package ru.chel.SRMPlayGround.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chel.SRMPlayGround.model.Orders;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {
    List<Orders> findByStatus(boolean status);
    List<Orders> findByDriversId(Long id);
    List<Orders> findByDriversIdAndStatus(Long id, boolean status);
    Orders findByCustomersIdAndDriversId(Long customer, Long driver);
}
