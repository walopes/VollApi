package med.voll.api.domain.consulta.validacoes;

public class ValidacaoMedicoAtivo{
    
    @AutoWire
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null)
		return;

	var isMedicoAtivo = repository.findAtivoById(dados.idMedico());
	if(!isMedicoAtivo)
	    throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");

    }
}
