package med.voll.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoMedicoComConsultaMesmoHorario implements ValidadorAgendamentoConsulta {

    private final String ERROR_MESSAGE = "Médico já possui outra consulta agendada nesse mesmo horário!";
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiConsultaHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiConsultaHorario)
            throw new ValidacaoException(ERROR_MESSAGE);
    }
}
