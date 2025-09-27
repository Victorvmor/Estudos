package br.com.AllTallent.dto;

import br.com.AllTallent.model.Competencia;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetenciaDTO {

    private Integer codigo;
    private String nome;

    // Construtor a partir da entidade
    public CompetenciaDTO(Competencia competencia) {
        this.codigo = competencia.getCodigo();
        this.nome = competencia.getNome();
    }

    // Conversor para entidade
    public Competencia toEntity() {
        Competencia competencia = new Competencia();
        competencia.setCodigo(this.codigo);
        competencia.setNome(this.nome);
        return competencia;
    }
}
