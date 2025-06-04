package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
                Long id,
                Long idMedico,
                Long idPaciente,
                LocalDateTime data) {

        public DadosDetalhamentoConsulta(Consulta consulta) {
                this.id = consulta.getId();
                this.idMedico = consulta.getMedico().getId();
                this.idPaciente = consulta.getPaciente().getId();
                this.data = consulta.getData();
        }

}
