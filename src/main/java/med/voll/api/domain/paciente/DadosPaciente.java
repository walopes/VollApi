package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.DadosEndereco;

public record DadosPaciente(String nome, String email, DadosEndereco endereco) {
}
