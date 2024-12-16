package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicosController {
    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder){
       Medico medico= medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datoRepues = new DatosRespuestaMedico(medico);
        URI uri= uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(datoRepues);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listarMedicos(@PageableDefault(size=10) Pageable paginacion){
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion)
                    .map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity modificarMedico(@RequestBody @Valid DatosActualizarMedico datosMedico){
        Medico medico= medicoRepository.getReferenceById(datosMedico.id());
        medico.actualizarDatos(datosMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    //ESTE METODO ELIMINA EL MEDICO A NIVEL LOGICO (LO DESACTIVA)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable @Valid Long id){
        Medico medico= medicoRepository.getReferenceById(id);
        medico.desactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaMedico> mostrarMedico(@PathVariable @Valid Long id){
//        Medico medico= medicoRepository.getReferenceById(id);
        Medico medico= medicoRepository.findByActivoTrueAndId(id);
        if(medico!= null) {
            return ResponseEntity.ok(new DatosRespuestaMedico(medico));
        }
        return ResponseEntity.notFound().build();
    }
}
