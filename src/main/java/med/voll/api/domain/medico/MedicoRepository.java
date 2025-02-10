package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable p);

    public final Integer HOURS_TO_CANCEL = 24;

    @Query("""
            select m from Medico m
            where m.ativo = true
            and m.especialidade = :especialidade
            and m.id not in (
            select c.medico.id from Consulta c
            where c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico findRandomMedico(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT con.id IS NOT NULL
            FROM Consulta con
            WHERE con.id = :idConsulta
            AND (TIMESTAMPDIFF(HOUR, :data, con.data) >= :HOURS_TO_CANCEL)
            """)
    Boolean isAllowedToCancelAppointment(Long idConsulta, LocalDateTime data);
}
