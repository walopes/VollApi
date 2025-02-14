package med.voll.api.domain.consulta.validacoes;

public class ValidadorHorarioAntecedencia{

    private final String errorMessage = "Consulta deve ser agendada com antecedência mínima de 30 minutos";
    
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
	var agora = localDateTime.now();
	var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

	if(diferencaEmMinutos < 30)
	    throw new ValidacaoException(this.errorMessage);
    }


}

