package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Valida si un medico tiene otra consulta en mismo horario
 *
 */
@Component
public class ValidarMedicoConConsultaMismoHorario implements ValidadorDeConsultas{
    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos){
        var tieneOtraConsultaMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(tieneOtraConsultaMismoHorario){
            throw new ValidacionException("Medico ya tiene otra consulta en esa fecha y hora");
        }

    }


}
