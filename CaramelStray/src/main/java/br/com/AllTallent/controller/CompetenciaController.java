package br.com.AllTallent.controller;

import br.com.AllTallent.dto.CompetenciaDTO;
import br.com.AllTallent.model.Competencia;
import br.com.AllTallent.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competencias")
public class CompetenciaController {

    @Autowired
    private CompetenciaRepository repository;

    // Criar uma nova competência
    @PostMapping
    public CompetenciaDTO create(@RequestBody CompetenciaDTO dto) {
        Competencia competencia = new Competencia();
        competencia.setNome(dto.getNome());

        Competencia saved = repository.save(competencia);
        return new CompetenciaDTO(saved.getCodigo(), saved.getNome());
    }

    // Listar todas
    @GetMapping
    public List<CompetenciaDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(c -> new CompetenciaDTO(c.getCodigo(), c.getNome()))
                .collect(Collectors.toList());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public CompetenciaDTO getById(@PathVariable Integer id) {
        Competencia competencia = repository.findById(id).orElseThrow();
        return new CompetenciaDTO(competencia.getCodigo(), competencia.getNome());
    }

    // Atualizar
    @PutMapping("/{id}")
    public CompetenciaDTO update(@PathVariable Integer id, @RequestBody CompetenciaDTO dto) {
        Competencia competencia = repository.findById(id).orElseThrow();
        competencia.setNome(dto.getNome());

        Competencia updated = repository.save(competencia);
        return new CompetenciaDTO(updated.getCodigo(), updated.getNome());
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
