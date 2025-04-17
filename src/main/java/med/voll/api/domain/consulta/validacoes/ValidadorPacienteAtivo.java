package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        // TODO
    }

    // private final String ERROR_MESSAGE = "Consulta não pode ser agendada com
    // médico excluído";

    // public void validar(DadosAgendamentoConsulta dados) {
    // if (dados.idMedico() == null)
    // return;

    // var isMedicoAtivo = repository.findAtivoById(dados.idMedico());
    // if (!isMedicoAtivo)
    // throw new ValidacaoException(ERROR_MESSAGE);

    // }
}
