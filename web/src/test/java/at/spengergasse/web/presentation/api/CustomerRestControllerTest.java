package at.spengergasse.web.presentation.api;

import at.spengergasse.web.commands.NewCustomerCommand;
import at.spengergasse.web.dto.CustomerDto;
import at.spengergasse.web.dto.PreorderDto;
import at.spengergasse.web.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {

    @MockitoBean
    private CustomerService customerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void can_get_all_customers() throws Exception {
        CustomerDto customerDto = new CustomerDto(
                123L,
                "Max",
                "Mustermann",
                "max@example.com",
                "123456789"
        );

        when(customerService.getAllCustomers()).thenReturn(List.of(customerDto));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void can_get_by_preorder_code() throws Exception {
        PreorderDto preorderDto = new PreorderDto(
                LocalDateTime.now(),
                12000L,
                List.of()
        );

        when(customerService.getPreorderByCode(any())).thenReturn(Optional.of(preorderDto));

        mockMvc.perform(get("/api/customers/preorder/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmountInCents").value(preorderDto.totalAmountInCents()));
    }

    @Test
    public void get_by_preorder_code_returns_bad_request_on_invalid_code() throws Exception {
        mockMvc.perform(get("/api/customers/preorder/123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void get_by_preorder_code_returns_not_found() throws Exception {
        when(customerService.getPreorderByCode(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/customers/preorder/12345"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void can_create_new_customer() throws Exception {
        CustomerDto customerDto = new CustomerDto(
                123L,
                "Max",
                "Mustermann",
                "max@example.com",
                "123456789"
        ) ;

        NewCustomerCommand customerCommand = new NewCustomerCommand(
                customerDto.firstName(),
                customerDto.lastName(),
                customerDto.email(),
                customerDto.phone()
        );

        when(customerService.createNewCustomer(any())).thenReturn(customerDto);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCommand)))
                .andExpect(status().isCreated());
    }

    @Test
    public void create_new_customer_returns_bad_request_on_invalid_body() throws Exception {
        NewCustomerCommand customerCommand = new NewCustomerCommand(
                "",
                "",
                "",
                ""
        );

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCommand)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void can_delete_customer() throws Exception {
        when(customerService.getCustomerById(any())).thenReturn(Optional.ofNullable(mock(CustomerDto.class)));
        when(customerService.getAllPreordersByCustomerId(any())).thenReturn(List.of());
        doNothing().when(customerService).deleteCustomer(any());

        mockMvc.perform(delete("/api/customers/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_customer_returns_not_found() throws Exception {
        when(customerService.getCustomerById(any())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/customers/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_customer_returns_bad_request_on_customer_has_preorders() throws Exception {
        when(customerService.getCustomerById(any())).thenReturn(Optional.ofNullable(mock(CustomerDto.class)));
        when(customerService.getAllPreordersByCustomerId(any())).thenReturn(List.of(mock(PreorderDto.class)));

        mockMvc.perform(delete("/api/customers/123"))
                .andExpect(status().isBadRequest());
    }
}
