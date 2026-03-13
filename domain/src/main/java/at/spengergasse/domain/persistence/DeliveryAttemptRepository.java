package at.spengergasse.domain.persistence;

import at.spengergasse.domain.domain.Customer;
import at.spengergasse.domain.domain.DeliveryAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAttemptRepository extends JpaRepository<DeliveryAttempt, DeliveryAttempt.DeliveryAttemptId> {
}
