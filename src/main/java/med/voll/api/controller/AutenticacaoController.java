package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.user.DadosAutenticacao;
import med.voll.api.domain.user.Usuario;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.GerarToken((Usuario)auth.getPrincipal()));
    }
}
