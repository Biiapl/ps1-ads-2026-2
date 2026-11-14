package br.edu.fatecfranca.api.services;


import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.CustomerRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CarRepository;
import br.edu.fatecfranca.api.repositories.CustomerRepository;



@Service
public class CustomerService {


   private final CustomerRepository repository;
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

   


   public CustomerService(CustomerRepository repository) {
       this.repository = repository;
   }


   public Customer create(CustomerRequest request) {
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

