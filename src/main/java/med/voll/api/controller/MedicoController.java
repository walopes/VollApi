package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(final MedicoRepository repo) {
        repository = repo;
    }


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder builder){
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = builder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("list")
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=5, sort= {"nome"}) Pageable p){
        var response = repository.findAllByAtivoTrue(p).map(DadosListagemMedico::new);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DadosUpdateMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.updateInfos(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
}