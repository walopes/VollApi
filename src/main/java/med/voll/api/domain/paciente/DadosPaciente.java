package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosPaciente(@NotNull Long id, String nome, String email, DadosEndereco endereco) {
}
