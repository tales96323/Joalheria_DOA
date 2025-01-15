package doa_jewelry;

import doa_jewelry.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "doa_jewelry")
@EntityScan(basePackages = "doa_jewelry.entity")
@EnableJpaRepositories(basePackages = "doa_jewelry.repository")
public class DoaJewelryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoaJewelryApplication.class, args);
		System.out.println("DOA Jewelry Store system initialized.");
	}
	@Bean
	CommandLineRunner initDatabase(CustomerService customerService, EmployeeService employeeService, JewelryService jewelryService, OrderService orderService, PaymentService paymentService) {
	return args -> {
	};

	}
}