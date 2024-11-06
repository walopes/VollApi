package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(final MedicoRepository repo) {
        repository = repo;
    }


    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping("list")
    public Page<DadosListagemMedico> listar(@PageableDefault(size=5, sort= {"nome"}) Pageable p){
        return repository.findAllByAtivoTrue(p).map(DadosListagemMedico::new);
    }

    @PutMapping("update")
    @Transactional
    public void update(@RequestBody @Valid DadosUpdateMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.updateInfos(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
        repository.save(medico);
    }
}