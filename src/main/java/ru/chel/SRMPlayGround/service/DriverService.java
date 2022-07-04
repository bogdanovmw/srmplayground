package ru.chel.SRMPlayGround.service;

import ru.chel.SRMPlayGround.dto.DriverDto;
import ru.chel.SRMPlayGround.model.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> findAll();
    Driver findById(Long id);
    Driver save(DriverDto driver);
    Driver update(Driver driver);
    Driver findByName(String name);
    void delete(Driver driver);
}
