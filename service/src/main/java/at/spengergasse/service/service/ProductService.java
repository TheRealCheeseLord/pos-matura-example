package at.spengergasse.service.service;

import at.spengergasse.service.dto.ProductWithRevenueDto;
import at.spengergasse.service.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductWithRevenueDto> getRevenueOfProduct(Long productId) {
        return productRepository.findById(productId)
                .map(ProductWithRevenueDto::new);
    }
}
