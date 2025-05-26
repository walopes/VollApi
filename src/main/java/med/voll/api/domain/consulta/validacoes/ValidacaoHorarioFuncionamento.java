package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoHorarioFuncionamento {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        // TODO Each appointment have 1h - 19pm is the closing time
        var depoisEncerramento = dataConsulta.getHour() > 18;

        if (domingo || antesDaAbertura || depoisEncerramento)
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
    }
}
