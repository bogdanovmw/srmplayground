package ru.chel.SRMPlayGround.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chel.SRMPlayGround.model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {
    Driver findByName(String name);
}
