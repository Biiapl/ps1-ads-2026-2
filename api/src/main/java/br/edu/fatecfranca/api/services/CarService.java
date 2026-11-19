
package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.repositories.CarRepository;
import br.edu.fatecfranca.api.validators.RequestValidator;
import br.edu.fatecfranca.api.validators.ManufactureYearValidator;
import br.edu.fatecfranca.api.validators.SaleDataValidator;
import br.edu.fatecfranca.api.validators.UniquePlatesValidator;

@Service
public class CarService {

    private final CarRepository repository;
    private final RequestValidator<CarRequest> validator;

    public CarService(CarRepository repository) {
        this.repository = repository;

        RequestValidator<CarRequest> platesValidator =
            new UniquePlatesValidator(repository, null);

        RequestValidator<CarRequest> saleValidator =
            new SaleDataValidator(platesValidator);

        this.validator = new ManufactureYearValidator(saleValidator);
    }

    public Car create(Car car, CarRequest request) {
        validator.validate(request, null);

        return repository.save(car);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public Car update(Car car, CarRequest request) {
        validator.validate(request, car.getId());

        return repository.save(car);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
