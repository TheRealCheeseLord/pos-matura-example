package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Driver;
import at.spengergasse.domain.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Person.PersonId> {
}
