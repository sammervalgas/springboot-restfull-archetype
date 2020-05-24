#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.carro;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public @NoArgsConstructor @Data class CarroDTO {

    private Long id;
    private String modelo;
    private String serie;
    private String placa;
    private String motoristaNome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dataEntradaMotorista;

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.modelo = carro.getModelo();
        this.serie = carro.getSerie();
        this.placa = carro.getPlaca();
        this.motoristaNome = carro.getMotorista().getNome();
        this.dataEntradaMotorista = carro.getMotorista().getDataEntrada();
    }

    public static Page<CarroDTO> converter(Page<Carro> carros) {
        return carros.map(CarroDTO::new);
    }

    public static CarroDTO converter(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }

}
