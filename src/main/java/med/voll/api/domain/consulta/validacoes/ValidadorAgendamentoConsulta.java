package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public interface ValidadorAgendamentoConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
