package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.DeliveryAttempt;
import at.spengergasse.domain.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Shipment.ShipmentId> {
}
