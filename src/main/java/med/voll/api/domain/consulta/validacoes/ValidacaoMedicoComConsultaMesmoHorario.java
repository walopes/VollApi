package med.voll.api.domain.consulta.validacoes;


public class ValidacaoMedicoComConsultaMesmoHorario{
	
	private ConsultaRepository repository;

	public  void validar(DadosAgendamentoConsulta dados){
	    var medicoPossuiConsultaHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
	    if(medicoPossuiConsultaHorario)
		    throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário!");
	}
}
