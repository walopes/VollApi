package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

    // TODO REmove Autowired for injection
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    public void agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepo.existsById(dados.idPaciente()))
            throw new ValidacaoException("Id do paciente não existe!");

        if (dados.idMedico() != null && !medicoRepo.existsById(dados.idMedico()))
            throw new ValidacaoException("Médico informado não existe no sistema!");

        var medico = obterMedico(dados);
        var paciente = pacienteRepo.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.date());
        repository.save(consulta);
    }

    private Medico obterMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null)
            return medicoRepo.getReferenceById(dados.idMedico());

        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");

        var medico = medicoRepo.findRandomMedico(dados.especialidade(), dados.date());
        return medico;
    }
}
