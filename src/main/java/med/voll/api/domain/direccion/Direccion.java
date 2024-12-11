package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Direccion {
    private String calle;
    private Integer numero;
    private String complemento;
    private String distrito;
    private String ciudad;

    public Direccion(DatosDireccion direccion) {
        this.calle= direccion.calle();
        this.numero = Integer.parseInt(direccion.numero());
        this.complemento= direccion.complemento();
        this.distrito= direccion.distrito();
        this.ciudad= direccion.ciudad();

    }

    public Direccion actualizar(Direccion direccion) {
        this.calle= direccion.getCalle();
        this.numero = direccion.getNumero();
        this.complemento= direccion.getComplemento();
        this.distrito= direccion.getDistrito();
        this.ciudad= direccion.getCiudad();
        return this;
    }

    public Direccion actualizar(DatosDireccion direccion) {
        this.calle= direccion.calle();
        this.numero = Integer.valueOf(direccion.numero());
        this.complemento= direccion.complemento();
        this.distrito= direccion.distrito();
        this.ciudad= direccion.ciudad();
        return this;
    }


}
