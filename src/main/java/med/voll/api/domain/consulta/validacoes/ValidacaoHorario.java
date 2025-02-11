package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidacaoHorario {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.date();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisEncerramento = dataConsulta.getHour() > 18; // Each appointment have 1h - 19pm is the closing time
    }
}
