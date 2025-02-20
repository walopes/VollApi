package med.voll.api.domain.consulta.validacoes;

public class ValidacaoMedicoAtivo{
    
    @AutoWire
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null)
		return;

    }
}
