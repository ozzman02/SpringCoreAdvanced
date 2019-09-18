package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;

@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<?> listAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	@Override
	public Customer getById(Integer id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return customerRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		customerRepository.delete(id);
	}

}
