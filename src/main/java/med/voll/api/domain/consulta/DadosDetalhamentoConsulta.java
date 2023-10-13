package med.voll.api.domain.consulta;

import java.time.LocalDateTime;


public record DadosDetalhamentoConsulta(Integer id, Integer idMedico, Integer idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
