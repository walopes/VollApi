package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosUpdateEndereco;

public record DadosUpdateMedico(
        @NotNull Long id,
        String telefone,
        String nome,
        DadosUpdateEndereco endereco) {
}
