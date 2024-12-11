package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;


@Data
@NoArgsConstructor
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String nombre;
    private String email;
    private String documento;
    private Boolean activo;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo= true;
        this.nombre= datosRegistroMedico.nombre();
        this.email= datosRegistroMedico.email();
        this.documento= datosRegistroMedico.documento();
        this.especialidad= datosRegistroMedico.especialidad();
        this.direccion= new Direccion(datosRegistroMedico.direccion());
        this.telefono= datosRegistroMedico.telefono();
    }

    public void actualizarDatos(DatosActualizarMedico datosMedico) {
        if (datosMedico.nombre()!=null){
            this.nombre= datosMedico.nombre();
        }
        if (datosMedico.documento()!=null){
            this.documento= datosMedico.documento();
        }
        if (datosMedico.direccion()!=null){
            this.direccion= direccion.actualizar(datosMedico.direccion());
        }
    }

    public void desactivar() {
        this.activo=false;
    }
}
