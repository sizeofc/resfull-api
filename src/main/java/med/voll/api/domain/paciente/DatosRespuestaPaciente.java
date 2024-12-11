package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        String  nombre,
        String  email,
        String  telefono,
        String  documentoIdentidad,
        DatosDireccion direccion
) {

    public DatosRespuestaPaciente(Paciente paciente){
        this(paciente.getNombre(), paciente.getTelefono(), paciente.getDocumentoIdentidad(),
                paciente.getEmail(),
                new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero()+"",
                        paciente.getDireccion().getComplemento()));
    }
}
