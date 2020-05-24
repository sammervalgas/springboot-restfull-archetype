package it.pkg.model.motorista;

import lombok.Data;
import lombok.NoArgsConstructor;

public @NoArgsConstructor @Data class MotoristaDTO {

    private Long id;
    private String nome;
    private Long numeroCnh;

    public MotoristaDTO(Motorista motorista) {
        this.id = motorista.getId();
        this.nome = motorista.getNome();
        this.numeroCnh = motorista.getCnh().getNumero();
    }
}
