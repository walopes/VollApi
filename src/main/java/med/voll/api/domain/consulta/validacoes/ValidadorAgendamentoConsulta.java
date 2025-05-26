package med.voll.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public interface ValidadorAgendamentoConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
