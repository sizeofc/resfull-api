package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
 @Query("SELECT COUNT(c) > 0 FROM Consulta c WHERE c.paciente.id = :pacienteId AND FUNCTION('DATE',c.fecha) = :fecha")
        boolean existsByPacienteIdAndFecha(Long pacienteId, LocalDate fecha);



    boolean existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(Long id, LocalDateTime fecha);
}
