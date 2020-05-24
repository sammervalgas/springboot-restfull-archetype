#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model.motorista;

import ${package}.model.carro.Carro;
import ${package}.model.cnh.Cnh;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public @NoArgsConstructor @Data class Motorista {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada = LocalDateTime.now();

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "cnh_id", referencedColumnName = "id")
    private Cnh cnh;

    @OneToMany(mappedBy = "motorista", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Carro> carros = new ArrayList<>();

    public Motorista(String nome, Cnh cnh) {
        this.nome = nome;
        this.cnh = cnh;
    }
}
