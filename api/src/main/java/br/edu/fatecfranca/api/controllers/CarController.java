
package br.edu.fatecfranca.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.repositories.CustomerRepository;
import br.edu.fatecfranca.api.services.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;
    private final CustomerRepository customerRepository;

    public CarController(
            CarService service,
            CustomerRepository customerRepository) {
        this.service = service;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody CarRequest request) {

        Car car = new Car();

        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setColor(request.color());
        car.setYearManufacture(request.yearManufacture());
        car.setImported(request.imported());
        car.setPlates(request.plates());
        car.setSellingDate(request.sellingDate());
        car.setSellingPrice(request.sellingPrice());

        if (request.customerId() != null) {
            var customer = customerRepository.findById(request.customerId());

            if (customer.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            car.setCustomer(customer.get());
        }

        Car savedCar = service.create(car, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCar);
    }

    @GetMapping
    public List<Car> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(
            @PathVariable Long id,
            @Valid @RequestBody CarRequest request) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Car car = new Car();

        car.setId(id);
        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setColor(request.color());
        car.setYearManufacture(request.yearManufacture());
        car.setImported(request.imported());
        car.setPlates(request.plates());
        car.setSellingDate(request.sellingDate());
        car.setSellingPrice(request.sellingPrice());

        if (request.customerId() != null) {
            var customer = customerRepository.findById(request.customerId());

            if (customer.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            car.setCustomer(customer.get());
        }

        return ResponseEntity.ok(service.update(car, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
