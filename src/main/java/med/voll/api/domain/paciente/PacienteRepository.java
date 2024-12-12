package med.voll.api.domain.paciente;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

boolean existsActivoById(Long id);
}
