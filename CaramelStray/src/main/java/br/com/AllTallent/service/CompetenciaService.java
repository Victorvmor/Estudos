package br.com.AllTallent.service;

import br.com.AllTallent.dto.CompetenciaDTO;
import br.com.AllTallent.exception.ResourceNotFoundException;
import br.com.AllTallent.model.Competencia;
import br.com.AllTallent.repository.CompetenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetenciaService {

    private final CompetenciaRepository competenciaRepository;

    public CompetenciaService(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    @Transactional(readOnly = true)
    public List<CompetenciaDTO> listarTodas() {
        return competenciaRepository.findAll().stream()
                .map(CompetenciaDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompetenciaDTO buscarPorId(Integer id) {
        Competencia competencia = competenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada com o ID: " + id));
        return new CompetenciaDTO(competencia);
    }

    @Transactional
    public CompetenciaDTO criar(CompetenciaDTO dto) {
        Competencia competencia = dto.toEntity();
        Competencia salva = competenciaRepository.save(competencia);
        return new CompetenciaDTO(salva);
    }

    @Transactional
    public CompetenciaDTO atualizar(Integer id, CompetenciaDTO dto) {
        Competencia existente = competenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competência não encontrada com o ID: " + id));

        existente.setNome(dto.getNome());
        Competencia salva = competenciaRepository.save(existente);
        return new CompetenciaDTO(salva);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!competenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Competência não encontrada com o ID: " + id);
        }
        competenciaRepository.deleteById(id);
    }
}
