package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCancelamentoConsulta(
        @NotNull Long idConsulta,
        @NotNull @Size(min = 12, max = 80, message = "Motivo deve ter entre 12 e 80 caracteres") String reason) {

}
