package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    Medico findByActivoTrueAndId(Long id);

    List<Medico> findByEspecialidad(Especialidad especialidad);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.activo=true
            AND m.especialidad = :especialidad
            AND m.id NOT IN(
                SELECT c.medico.id FROM Consulta c 
                WHERE c.fecha = :fecha
                AND 
                c.motivoCancelamiento is null 
            )
            ORDER BY rand()
            limit 1 
            
""")
    Medico elegirMedicoAleatorioDisponibleEnFecha(Especialidad especialidad, LocalDateTime fecha);

    boolean existsActivoById(Long id);
}
