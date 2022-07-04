package ru.chel.SRMPlayGround.service;

import ru.chel.SRMPlayGround.dto.OrdersDto;
import ru.chel.SRMPlayGround.model.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll();
    List<Orders> findAllByDriverId(Long id);
    Orders findById(Long id);
    Orders save(OrdersDto orders);
    void delete(Orders orders);
    Orders findByCustomerIdAndDriverId(Long customer, Long driver);
    List<Orders> findByDriversIdAndStatus(Long id, boolean status);
    List<Orders> findAllAndStatus(boolean status);
    void deleteAll(List<Orders> orders);
}
