package at.spengergasse.service.persistence;

import at.spengergasse.service.domain.PreorderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreorderItemRepository {

    Optional<PreorderItem> findById(Long id);
    List<PreorderItem> findAll();

}
