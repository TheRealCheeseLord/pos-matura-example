package at.spengergasse.service.persistence;

import at.spengergasse.service.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {

    Optional<Product> findById(Long id);
    List<Product> findAll();

}
