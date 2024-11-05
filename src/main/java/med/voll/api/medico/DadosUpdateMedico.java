package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.DadosUpdateEndereco;

public record DadosUpdateMedico(
        @NotNull Long id,
        String telefone,
        String nome,
        DadosUpdateEndereco endereco) {
}
