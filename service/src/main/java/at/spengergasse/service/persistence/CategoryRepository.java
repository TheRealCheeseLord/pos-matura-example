package at.spengergasse.service.persistence;

import at.spengergasse.service.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository {

    Optional<Category> findById(Long id);
    List<Category> findAll();

}
