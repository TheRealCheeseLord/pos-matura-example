package at.spengergasse.service.service;

import at.spengergasse.service.dto.PreorderDto;
import at.spengergasse.service.persistence.PreorderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class PreorderService {

    private final PreorderRepository preorderRepository;

    public List<PreorderDto> getPreordersOfCustomer(Long customerId) {
        return preorderRepository.findAllByCustomerId(customerId).stream()
                .map(PreorderDto::new)
                .toList();
    }
}
