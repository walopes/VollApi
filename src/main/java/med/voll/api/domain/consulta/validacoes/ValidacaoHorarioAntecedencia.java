package med.voll.api.domain.consulta.validacoes;

public class ValidadorHorarioAntecedencia{

    private final Int DURATION_TIME = 30;
    private final String ERROR_MESSAGE = "Consulta deve ser agendada com antecedência mínima de 30 minutos";
    
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
	var agora = localDateTime.now();
	var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

	if(diferencaEmMinutos < DURATION_TIME)
	    throw new ValidacaoException(this.ERROR_MESSAGE);
    }


}

