#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.cnh;

import ${package}.enums.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public @NoArgsConstructor @Data class Cnh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Min(value = 2)
    private Long numero;

    @Enumerated(EnumType.STRING)
    private Categoria categoria = Categoria.D;


    public Cnh(Long numero, Categoria categoria) {
        this.numero = numero;
        this.categoria = categoria;
    }
}
