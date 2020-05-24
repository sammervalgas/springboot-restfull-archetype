package it.pkg.model.carro;

import it.pkg.model.motorista.Motorista;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public @NoArgsConstructor @Data class Carro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty
    private String placa;

    @NotNull @NotEmpty
    private String modelo;

    @NotNull @NotEmpty
    private String serie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Motorista motorista;

    public Carro(String placa,
                 String modelo,
                 String serie,
                 Motorista motorista) {
        this.placa = placa;
        this.modelo = modelo;
        this.serie = serie;
        this.motorista = motorista;
    }
}
