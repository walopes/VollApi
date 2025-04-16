package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.PacienteRepository;

public class ConsultaPacienteAtivo {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosPaciente paciente) {
        // TODO
    }
}
