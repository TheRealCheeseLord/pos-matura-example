package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Depot;
import at.spengergasse.domain.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Driver.DriverId> {
}
