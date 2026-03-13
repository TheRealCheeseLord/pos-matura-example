package at.spengergasse.service.persistence;

import at.spengergasse.service.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository {

    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Customer save(Customer customer);

}
