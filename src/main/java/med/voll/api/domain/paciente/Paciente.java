package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
	private String ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosPaciente data){
        this.nome = data.nome();
        this.email = data.email();
        this.endereco = new Endereco(data.endereco());
    }
	
    public void updateInfos(@Valid DadosUpdatePaciente paciente) {
        if(paciente.nome() != null) {
            this.nome = paciente.nome();
        }
        if(paciente.email() != null) {
            this.email = paciente.email();
        }
        if(paciente.endereco() != null) {
            this.endereco.updateInfos(paciente.endereco());
        }
    }
	
    public void excluir() {
        this.ativo = false;
    }
}