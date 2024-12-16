package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DatosRespuestaMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public Page<DatosListaPaciente> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaPaciente::new);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> registrar(@RequestBody @Valid DatosRegistroPaciente datos,
                                    UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente=repository.save(new Paciente(datos));
        DatosRespuestaPaciente datosRep= new DatosRespuestaPaciente(paciente);
        URI url= uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRep);
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        var paciente = repository.getReferenceById(datos.id());
        paciente.atualizarInformacion(datos);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inactivar();
    }


    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaPaciente> mostrarPaciente(@PathVariable @Valid Long id){
        Paciente paciente= repository.getReferenceById(id);
        if(paciente!= null) {
            return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
        }
        return ResponseEntity.notFound().build();
    }

}