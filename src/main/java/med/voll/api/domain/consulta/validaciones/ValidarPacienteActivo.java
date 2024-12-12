package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteActivo implements ValidadorDeConsultas {
    @Autowired
    PacienteRepository pacienteRepository;
    public void validar(DatosReservaConsulta datos){
        var paciente = pacienteRepository.existsActivoById(datos.idPaciente());
        if(!paciente){
            throw new ValidacionException("La consulta no puede ser reservada con paciente excluido!");
        }
    }
}
