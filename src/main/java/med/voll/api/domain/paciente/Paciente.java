package med.voll.api.domain.paciente;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Data
@Entity
@Table(name = "pacientes")
public class Paciente{
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    @Embedded
    private Direccion direccion;

    private Boolean activo;

    public Paciente(DatosRegistroPaciente datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void atualizarInformacion(DatosActualizacionPaciente datos) {
        if (datos.nombre() != null)
            this.nombre = datos.nombre();

        if (datos.telefono() != null)
            this.telefono = datos.telefono();

        if (datos.direccion() != null)
            direccion.actualizar(datos.direccion());
    }

    public void inactivar() {
        this.activo = false;
    }


}
