package med.voll.api.paciente;

public record DadosDetalhamentoPaciente(Long id, String nome, String email) {
	
	public DadosDetalhamentoPaciente(Paciente paciente){
		this(
			paciente.getId(),
			paciente.getNome(),
			paciente.getEmail()
		);
		
	}
}
