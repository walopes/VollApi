package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoMedicoAtivo{
  
    // TODO Remove this AutoWire injection	
    @AutoWire	
    private MedicoRepository repository;

    private final String ERROR_MESSAGE = "Consulta não pode ser agendada com médico excluído";

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null)
		return;

	var isMedicoAtivo = repository.findAtivoById(dados.idMedico());
	if(!isMedicoAtivo)
	    throw new ValidacaoException(ERROR_MESSAGE);
    }
}
