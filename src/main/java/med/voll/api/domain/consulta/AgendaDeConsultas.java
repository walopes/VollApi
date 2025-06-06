package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsulta;

@Service
public class AgendaDeConsultas {

    // TODO REmove Autowired for injection
    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepo;

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepo.existsById(dados.idPaciente()))
            throw new ValidacaoException("Id do paciente não existe!");

        if (dados.idMedico() != null && !medicoRepo.existsById(dados.idMedico()))
            throw new ValidacaoException("Médico informado não existe no sistema!");

        validadores.forEach(v -> v.validar(dados));

        var medico = obterMedico(dados);
        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }
        var paciente = pacienteRepo.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data());
        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico obterMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null)
            return medicoRepo.getReferenceById(dados.idMedico());

        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");

        var medico = medicoRepo.findRandomMedico(dados.especialidade(), dados.data());
        return (Medico) medico;
    }

    public void cancelarAgenda(DadosCancelamentoConsulta dados) {
        if (dados == null)
            throw new ValidacaoException("Dados nulos");

        /*
         * TODO Implement next validations:
         * O sistema deve possuir uma funcionalidade que permita o cancelamento de
         * consultas, na qual as seguintes informações deverão ser preenchidas:
         * Consulta
         * Motivo do cancelamento
         * As seguintes regras de negócio devem ser validadas pelo sistema:
         * É obrigatório informar o motivo do cancelamento da consulta, dentre as
         * opções: paciente desistiu, médico cancelou ou outros;
         * Uma consulta somente poderá ser cancelada com antecedência mínima de 24
         * horas.
         */
    }
}
