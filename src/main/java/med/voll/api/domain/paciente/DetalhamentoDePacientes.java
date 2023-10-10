package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DetalhamentoDePacientes(


         Integer id,
         String nome,
         String email,
         String cpf,
         String telefone,
         Boolean ativo,

         Endereco endereco


)

{
    public DetalhamentoDePacientes(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(),paciente.getAtivo(), paciente.getEndereco());
    }

}
