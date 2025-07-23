package med.voll.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;

@Component
public interface ValidadorCancelamentoConsultas {
    void validar(DadosCancelamentoConsulta dados);
}
