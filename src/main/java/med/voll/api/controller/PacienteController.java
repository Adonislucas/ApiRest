package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity adiciona(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        repository.save(paciente);

       var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();


        return ResponseEntity.created(uri).body(new DetalhamentoDePacientes(paciente));


    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemPaciente>> lista(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginação){
        var page = repository.findAllByAtivoTrue(paginação)
                .map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPaciente dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalhamentoDePacientes(paciente));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity apagar(@PathVariable Integer id){
        var paciente = repository.getReferenceById(id);
        paciente.desativar();

        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable  Integer id) {
        var paciente = repository.getReferenceById(id);


        return ResponseEntity.ok(new DetalhamentoDePacientes(paciente));


    }
}
