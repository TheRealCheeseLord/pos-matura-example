package at.spengergasse.web.presentation.api;

import at.spengergasse.web.commands.NewCustomerCommand;
import at.spengergasse.web.dto.CustomerDto;
import at.spengergasse.web.dto.PreorderDto;
import at.spengergasse.web.service.CustomerService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {


    private final Validator validator;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("preorder/{code}")
    public ResponseEntity<PreorderDto> getPreorderByCode(@PathVariable String code) {
        if (code.length() < 5) return ResponseEntity.badRequest().build();

        return customerService.getPreorderByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody NewCustomerCommand newCustomerCommand) {
        if (!validator.validate(newCustomerCommand).isEmpty()) return ResponseEntity.badRequest().build();
        var customer = customerService.createNewCustomer(newCustomerCommand);
        return ResponseEntity.created(
                URI.create("/api/customers/%d".formatted(customer.id()))
        ).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        var customer = customerService.getCustomerById(id);

        if (customer.isEmpty()) return ResponseEntity.notFound().build();

        if (!customerService.getAllPreordersByCustomerId(id).isEmpty()) return ResponseEntity.badRequest().build();

        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
