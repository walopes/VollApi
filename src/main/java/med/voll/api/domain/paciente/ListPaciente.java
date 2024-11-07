package med.voll.api.domain.paciente;

public record ListPaciente(String nome, String email) {

    public ListPaciente(Paciente p) {
        this(p.getNome(), p.getEmail());
    }
}
