package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidacaoHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    private final int DURATION_TIME = 30;
    private final String ERROR_MESSAGE = "Consulta deve ser agendada com antecedência mínima de 30 minutos";

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < DURATION_TIME)
            throw new ValidacaoException(this.ERROR_MESSAGE);
    }

}
