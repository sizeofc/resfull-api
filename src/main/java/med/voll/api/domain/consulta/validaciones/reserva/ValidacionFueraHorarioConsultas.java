package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

/**
 * Valida que el horario de la reserva este dentro del horario de la clinica
 * que es de Lunes a Sabados de 07 a 19Hs
 */
@Component
public class ValidacionFueraHorarioConsultas implements ValidadorDeConsultas{

    public void validar(DatosReservaConsulta datos){
        var fechaConsulta= datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesApertura= fechaConsulta.getHour()<7;
        var horarioDespuesDeCierre = fechaConsulta.getHour()>18;
        if (domingo||horarioAntesApertura||horarioDespuesDeCierre){
            throw new ValidacionException("Horario seleccionado fuera del horario de atencion de la clinica");
        }
    }
}
