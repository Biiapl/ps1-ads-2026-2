
package br.edu.fatecfranca.api.validators;

import java.time.Year;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.dtos.CarRequest;

public class ManufactureYearValidator extends RequestValidator<CarRequest> {

    public ManufactureYearValidator(RequestValidator<CarRequest> next) {
        super(next);
    }

    @Override
    protected void check(CarRequest request, Long carId) {

        if (request.yearManufacture() != null &&
            (request.yearManufacture() < 1900 ||
             request.yearManufacture() > Year.now().getValue())) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O ano de fabricação deve estar entre 1900 e o ano atual."
            );
        }
    }
}
