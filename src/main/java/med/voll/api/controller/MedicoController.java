package med.voll.api.controller;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping("listar")
    public Page<DadosListagemMedico> listar(Pageable p){
        System.out.println(p.toString());
        return repository.findAll(p).map(DadosListagemMedico::new);
    }
}