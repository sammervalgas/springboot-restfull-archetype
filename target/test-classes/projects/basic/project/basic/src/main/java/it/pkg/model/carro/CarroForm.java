package it.pkg.model.carro;

import it.pkg.model.motorista.Motorista;
import it.pkg.repository.MotoristaRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public @NoArgsConstructor @Data  class CarroForm {

    @NotNull @NotEmpty
    private String placa;

    @NotNull @NotEmpty
    private String modelo;

    @NotNull @NotEmpty
    private String serie;

    @NotNull
    private Long idMotorista;

    public Carro converter(MotoristaRepository motoristaRepository) {
        Optional<Motorista> motorista = motoristaRepository.findById(idMotorista);
        if(motorista.isPresent()) {
            return new Carro(placa, modelo, serie, motorista.get());
        }
        throw new RuntimeException();
    }
}
