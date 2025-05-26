package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidacaoMedicoAtivo implements ValidadorAgendamentoConsulta {

    // TODO Remove this AutoWire injection
    @Autowired
    private MedicoRepository repository;

    private final String ERROR_MESSAGE = "Consulta não pode ser agendada com médico excluído";

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null)
            return;

        var isMedicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!isMedicoAtivo)
            throw new ValidacaoException(ERROR_MESSAGE);
    }
}
