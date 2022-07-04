package ru.chel.SRMPlayGround.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chel.SRMPlayGround.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
}
