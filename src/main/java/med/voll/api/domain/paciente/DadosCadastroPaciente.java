package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank(message = "{nome.obrigatorio}") String nome,
        @NotBlank(message = "{email.obirgatorio}") @Email(message = "{email.invalido}") String email,
        @NotNull @Valid DadosEndereco endereco) {
}