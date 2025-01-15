package doa_jewelry.service;

import doa_jewelry.entity.Customer;
import doa_jewelry.exception.EntityAlreadyExistsException;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private doa_jewelry.entity.Customer Customer;

    public Customer saveClient(Customer cliente) throws EntityAlreadyExistsException {
        if (cliente.getId() != null && customerRepository.existsById(cliente.getId())) {
            throw new EntityAlreadyExistsException(Customer.class);
        }
        if (customerRepository.findByNIF(cliente.getNif()).isPresent()) {
            throw new EntityAlreadyExistsException(Customer.class);
        }
        return customerRepository.save(cliente);
    }

    public Optional<Customer> findCustomerById(String customerId) {
        return customerRepository.findByNIF(customerId);
    }

    public Customer updateClient(Customer cliente) throws EntityNotFoundException {
        if (!customerRepository.existsById(cliente.getId())) {
            throw new EntityNotFoundException(Customer.class);
        }
        return customerRepository.save(Customer);
    }

    public List<Customer> getAllClients() {
        return customerRepository.findAll();
    }

    public doa_jewelry.entity.Customer getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    public void deleteCustomer(Long id) {
    }
}