package at.spengergasse.web;

import at.spengergasse.web.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class WebApplicationTests {

    @MockitoBean
    private CustomerService customerService;

    @Test
    void contextLoads() {}
}
