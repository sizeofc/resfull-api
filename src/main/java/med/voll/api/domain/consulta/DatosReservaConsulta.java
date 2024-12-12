package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future  //es para indicar que las fechas no pueden ser menores a la actual
        //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha,
        Especialidad especialidad
) {
}
