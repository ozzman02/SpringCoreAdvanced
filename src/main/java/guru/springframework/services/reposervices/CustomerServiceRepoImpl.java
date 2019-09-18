package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;

@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	private CustomerFormToCustomer customerFormToCustomer;
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Autowired
    public void setCustomerFormToCustomer(CustomerFormToCustomer customerFormToCustomer) {
        this.customerFormToCustomer = customerFormToCustomer;
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

	@Override
	public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
		Customer newCustomer = customerFormToCustomer.convert(customerForm);
        if(newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());
            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(newCustomer);
	}

}
