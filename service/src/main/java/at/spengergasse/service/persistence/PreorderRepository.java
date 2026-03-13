package at.spengergasse.service.persistence;

import at.spengergasse.service.domain.Preorder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreorderRepository {

    Optional<Preorder> findById(Long id);
    List<Preorder> findAll();
    List<Preorder> findAllByCustomerId(Long id);

}
