package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidacionConsultaAnticipacion implements ValidadorDeConsultas{

    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos= Duration.between(ahora,fechaConsulta).toMinutes();
        if(diferenciaEnMinutos<30||datos.fecha().getHour()==18){
            throw new ValidacionException("""
                La reserva no puede realizarse con menos de 30 minutos de anticipacion
                o una hora antes del cierre de la clinica
                """);
        }
    }
}
