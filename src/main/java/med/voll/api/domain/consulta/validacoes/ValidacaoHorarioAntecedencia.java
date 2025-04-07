package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidacaoHorarioAntecedencia {

    private final int DURATION_TIME = 30;
    private final String ERROR_MESSAGE = "Consulta deve ser agendada com antecedência mínima de 30 minutos";

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < DURATION_TIME)
            throw new ValidacaoException(this.ERROR_MESSAGE);
    }

}
