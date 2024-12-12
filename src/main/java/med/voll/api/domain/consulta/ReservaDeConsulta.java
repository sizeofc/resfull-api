package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void reservar(DatosReservaConsulta datos){
        if(datos.idMedico()!=null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe el medico con el id solicitado!");
        }

        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe el paciente con el id solicitado");
        }

        Medico medico= elejirMedico(datos);
        Paciente paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta= new Consulta(null,medico,paciente,datos.fecha(),null);

        consultaRepository.save(consulta);
    }

    private Medico elejirMedico(DatosReservaConsulta datos) {
        if(datos.idMedico()!=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se selecciona un medico");
        }
        return medicoRepository.elegirMedicoAleatorioDisponibleEnFecha(datos.especialidad(),datos.fecha());
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
