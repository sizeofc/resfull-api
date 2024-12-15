package med.voll.api.domain.consulta.validaciones.cancelamiento;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorConsultaConAnticipacionCancelamiento")
public class ValidarConsultaAnticipacion implements ValidadorCancelamientoDeConsulta{

    @Autowired
    ConsultaRepository consultaRepository;
    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        var consulta = consultaRepository.findById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaHoras = Duration.between(ahora,consulta.get().getFecha()).toHours();
        if(diferenciaHoras<24){
            throw new ValidacionException("La cancelacion solo puede realizarse con al menos 24hs de anticipacion");
        }

    }
}
