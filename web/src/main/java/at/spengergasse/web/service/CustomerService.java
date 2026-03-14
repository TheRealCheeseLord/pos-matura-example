package at.spengergasse.web.service;

import at.spengergasse.web.commands.NewCustomerCommand;
import at.spengergasse.web.dto.CustomerDto;
import at.spengergasse.web.dto.PreorderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    Optional<CustomerDto> getCustomerById(Long customerId);

    List<PreorderDto> getAllPreordersByCustomerId(Long customerId);

    Optional<PreorderDto> getPreorderByCode(String code);

    CustomerDto createNewCustomer(NewCustomerCommand command);

    void deleteCustomer(Long customerId);
}
