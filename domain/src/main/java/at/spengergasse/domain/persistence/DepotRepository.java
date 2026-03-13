package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Depot.DepotId> {
}
