package at.spengergasse.service.service;

import at.spengergasse.service.domain.Preorder;
import at.spengergasse.service.domain.PreorderItem;
import at.spengergasse.service.dto.CustomerProductPreorder;
import at.spengergasse.service.persistence.CustomerRepository;
import at.spengergasse.service.persistence.PreorderRepository;
import at.spengergasse.service.persistence.ProductRepository;
import at.spengergasse.service.service.exception.OnlineStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.random.RandomGenerator;

@RequiredArgsConstructor

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Preorder addPreorder(Long customerId, List<CustomerProductPreorder> productPreorders) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> OnlineStoreException.forInvalidCustomerId(customerId));

        if (productPreorders.isEmpty()) throw OnlineStoreException.forEmptyPreorders();

        List<PreorderItem> preorders = productPreorders.stream()
                .map(productPreorder -> {
                    var product = productRepository.findById(productPreorder.productId());
                    return product.map(value -> new PreorderItem(
                            null,
                            null,
                            value,
                            productPreorder.quantity(),
                            value.priceInCents()
                    )).orElse(null);
                })
                .filter(Objects::nonNull)
                .toList();

        Preorder preorder = new Preorder(
                null,
                customer,
                null,
                LocalDateTime.now(),
                preorders.stream()
                        .mapToLong(value -> value.quantity() * value.unitPriceInCents())
                        .sum(),
                preorders
        );

        customer.preorders().add(preorder);
        customerRepository.save(customer);

        return preorder;
    }
}
