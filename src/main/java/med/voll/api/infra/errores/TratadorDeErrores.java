package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosError::new);
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler
    public ResponseEntity tratarErroreValidacion(ValidacionException e){

    return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosError(String campo,String mensaje){
        DatosError(FieldError error){
            this(error.getField(),error.getDefaultMessage());

        }
    }
}
