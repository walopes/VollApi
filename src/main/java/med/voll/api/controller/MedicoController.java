package med.voll.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.DadosUpdateMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> get(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        if(medico == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=5, sort= {"nome"}) Pageable p){
        var response = repository.findAllByAtivoTrue(p).map(DadosListagemMedico::new);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> update(@RequestBody @Valid DadosUpdateMedico dados){
        var entity = repository.getReferenceById(dados.id());
        entity.updateInfos(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(entity));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
}