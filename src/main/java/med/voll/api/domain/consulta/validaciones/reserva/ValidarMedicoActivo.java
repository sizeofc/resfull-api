package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoActivo implements ValidadorDeConsultas{
    @Autowired
    MedicoRepository medicoRepository;
    public void validar(DatosReservaConsulta datos){

        if(datos.idMedico()== null){
            return;
        }
        var medico = medicoRepository.existsActivoById(datos.idMedico());
        if(!medico){
            throw new ValidacionException("La consulta no puede ser reservada con medico excluido!");
        }
    }
}

