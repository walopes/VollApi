package med.voll.api.controller;

import med.voll.api.domain.paciente.DadosPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
