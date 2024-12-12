package med.voll.api.controller;

import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.ListPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("paciente")
public class PacienteController{

	public PacienteController(PacienteRepository repository){
		pacienteRepository = repository;
	}

    @PostMapping
    public void cadastro(@RequestBody DadosPaciente paciente) {
        pacienteRepository.save(new Paciente(paciente));
    }

    @GetMapping("list")
    public Page<ListPaciente> listar(@PageableDefault(sort={"name"}, size = 1, direction = Sort.Direction.DESC) Pageable pageable) {
        return pacienteRepository.findAllByAtivoTrue(pageable).map(ListPaciente::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> get(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
	
	@PutMapping("/update")
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> put(@RequestBody @Valid DadosPaciente paciente){
		//TODO Create a record for that method

	}
	
	@DeleteMapping()
	@Transactional
	public ResponseEntity<> delete(@PathVariable Long id){
		var entity = repository.getReferenceById(id);
		entity.excluir();
		
		return ResponseEntity.noContent().build();
	}
}
