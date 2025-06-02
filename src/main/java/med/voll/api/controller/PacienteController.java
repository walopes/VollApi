package med.voll.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.DadosUpdatePaciente;
import med.voll.api.domain.paciente.ListPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository repository) {
        pacienteRepository = repository;
    }

    // TODO Implement properly the methods
    @PostMapping
    @Secured("ROLE_ADMIN")
    public void cadastro(@RequestBody DadosPaciente paciente) {
        pacienteRepository.save(new Paciente(paciente));
    }

    @GetMapping("list")
    public Page<ListPaciente> listar(
            @PageableDefault(sort = { "name" }, size = 1, direction = Sort.Direction.DESC) Pageable pageable) {
        return pacienteRepository.findAllByAtivoTrue(pageable).map(ListPaciente::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> get(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping("/update")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DadosDetalhamentoPaciente> update(@RequestBody @Valid DadosUpdatePaciente paciente) {
        var entity = pacienteRepository.getReferenceById(paciente.id());
        entity.updateInfos(paciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(entity));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id) {
        var entity = pacienteRepository.getReferenceById(id);
        entity.excluir();

        return ResponseEntity.noContent().build();
    }
}
