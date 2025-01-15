package doa_jewelry.startup;

import doa_jewelry.entity.*;
import doa_jewelry.exception.EntityAlreadyExistsException;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.service.CustomerService;
import doa_jewelry.service.EmployeeService;
import doa_jewelry.service.JewelryService;
import doa_jewelry.service.OrderService;
import doa_jewelry.service.PaymentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class StartupInitializer {

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final JewelryService jewelryService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    @Autowired
    public StartupInitializer(CustomerService customerService, EmployeeService employeeService, JewelryService jewelryService, OrderService orderService, PaymentService paymentService) {

        this.customerService = customerService;
        this.employeeService = employeeService;
        this.jewelryService = jewelryService;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }


    public void initializeData() {
    }
}
