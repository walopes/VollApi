package med.voll.api.controller;

import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.ListPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.paciente.DadosDetalhamentoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO Adicionar os outros métodos
// TODO Adicionar tratativa de erros
// TODO Melhorar a injeção de dependência

@RestController
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    public void cadastro(@RequestBody DadosPaciente paciente) {
        // System.out.println(paciente);
        pacienteRepository.save(new Paciente(paciente));
    }

    @GetMapping("list")
    public Page<ListPaciente> listar(@PageableDefault(sort={"name"}, size = 1, direction = Sort.Direction.DESC) Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(ListPaciente::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> get(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
