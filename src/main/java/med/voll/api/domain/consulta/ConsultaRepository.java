package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {


    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Integer integer, LocalDateTime data);


    Boolean existsByPacienteIdAndDataBetween(Integer integer, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
