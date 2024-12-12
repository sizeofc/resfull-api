package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidarCantidadConsultasPaciente implements ValidadorDeConsultas{
    @Autowired
    ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsulta datos){
        var fechaConsulta= datos.fecha();
        var consultas = consultaRepository.existsByPacienteIdAndFecha(datos.idPaciente(), fechaConsulta.toLocalDate());
        if(consultas){
            throw new ValidacionException("El paciente no puede realizarse mas de una consulta en el mismo dia!");
        }
    }
}
