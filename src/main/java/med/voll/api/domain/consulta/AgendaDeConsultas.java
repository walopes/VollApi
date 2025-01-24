package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    public void agendar(DadosAgendamentoConsulta dados) {

        // TODO Add the business logic

        var medico = medicoRepo.findById(dados.idMedico()).get();
        var paciente = pacienteRepo.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.date());
        repository.save(consulta);
    }
}
