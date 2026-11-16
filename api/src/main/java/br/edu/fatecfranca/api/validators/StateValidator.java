package br.edu.fatecfranca.api.validators;


import java.util.Set;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import br.edu.fatecfranca.api.dtos.CustomerRequest;


public class StateValidator extends RequestValidator<CustomerRequest> {


   private static final Set<String> VALID_STATES = Set.of(
       "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
       "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
       "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
   );


   public StateValidator(RequestValidator<CustomerRequest> next) {
       super(next);
   }


   @Override
   protected void check(CustomerRequest request, Long customerId) {
       if (!VALID_STATES.contains(request.state())) {
           throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST,
               "A UF informada não existe."
           );
       }
   }
}
