
package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.CustomerRequest;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CustomerRepository;

import br.edu.fatecfranca.api.validators.RequestValidator;
import br.edu.fatecfranca.api.validators.StateValidator;
import br.edu.fatecfranca.api.validators.UniqueDocumentValidator;
import br.edu.fatecfranca.api.validators.UniqueEmailValidator;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final RequestValidator validator;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;

        RequestValidator emailValidator =
            new UniqueEmailValidator(repository, null);

        RequestValidator documentValidator =
            new UniqueDocumentValidator(repository, emailValidator);

        this.validator = new StateValidator(documentValidator);
    }

    private void copyToEntity(
        CustomerRequest request,
        Customer customer) {

        customer.setName(request.name());
        customer.setIdentDocument(request.identDocument());
        customer.setBirthDate(request.birthDate());
        customer.setStreetName(request.streetName());
        customer.setHouseNumber(request.houseNumber());
        customer.setComplements(request.complements());
        customer.setDistrict(request.district());
        customer.setMunicipality(request.municipality());
        customer.setState(request.state());
        customer.setPhone(request.phone());
        customer.setEmail(request.email());
    }

    public Customer create(CustomerRequest request) {

        validator.validate(request, null);

        Customer customer = new Customer();
        copyToEntity(request, customer);

        return repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public Customer update(Long id, CustomerRequest request) {

        validator.validate(request, id);

        Customer customer = new Customer();
        copyToEntity(request, customer);
        customer.setId(id);

        return repository.save(customer);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
