package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosUpdateEndereco;

public record DadosUpdatePaciente(@NotNull Long id,
    String nome,
    String email,
    DadosUpdateEndereco endereco
    ) 
{
}