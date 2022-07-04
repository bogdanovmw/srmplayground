package ru.chel.SRMPlayGround.service.impl;

import org.springframework.stereotype.Service;
import ru.chel.SRMPlayGround.dto.DriverDto;
import ru.chel.SRMPlayGround.model.Driver;
import ru.chel.SRMPlayGround.repostitory.DriverRepo;
import ru.chel.SRMPlayGround.service.DriverService;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepo driverRepo;

    public DriverServiceImpl(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }


    @Override
    public List<Driver> findAll() {
        return driverRepo.findAll();
    }

    @Override
    public Driver findById(Long id) {
        return driverRepo.findById(id).orElse(null);
    }

    @Override
    public Driver save(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setName(driverDto.getName());
        return driverRepo.save(driver);
    }

    @Override
    public Driver update(Driver driver) {
        return driverRepo.save(driver);
    }

    @Override
    public Driver findByName(String name) {
        return driverRepo.findByName(name);
    }

    @Override
    public void delete(Driver driver) {
        driverRepo.delete(driver);
    }
}
