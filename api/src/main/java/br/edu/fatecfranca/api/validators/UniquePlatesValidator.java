
package br.edu.fatecfranca.api.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.repositories.CarRepository;

public class UniquePlatesValidator extends RequestValidator<CarRequest> {

    private final CarRepository repository;

    public UniquePlatesValidator(
            CarRepository repository,
            RequestValidator<CarRequest> next) {
        super(next);
        this.repository = repository;
    }

    @Override
    protected void check(CarRequest request, Long carId) {

        boolean exists;

        if (carId == null) {
            exists = repository.existsByPlates(request.plates());
        } else {
            exists = repository.existsByPlatesAndIdNot(
                request.plates(),
                carId
            );
        }

        if (exists) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe um veículo cadastrado com essa placa."
            );
        }
    }
}
