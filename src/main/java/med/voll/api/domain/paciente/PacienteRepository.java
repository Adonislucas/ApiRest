package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository <Paciente, Integer> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginação);


    @Query("""
    select p.ativo
    from Paciente p
    where
    p.id = :id
        """)
    Boolean findAtivoById(Integer id);
}
