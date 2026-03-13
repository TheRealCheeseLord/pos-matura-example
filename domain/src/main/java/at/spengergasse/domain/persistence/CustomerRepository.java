package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Customer;
import at.spengergasse.domain.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Person.PersonId> {
}
