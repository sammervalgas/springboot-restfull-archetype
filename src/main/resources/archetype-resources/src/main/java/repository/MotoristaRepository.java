#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.enums.Categoria;
import ${package}.model.motorista.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    @Query("select m from Motorista m where m.cnh.categoria = :categoria")
    List<Motorista> buscaCnhPorCategoria(@Param("categoria") Categoria categoria);

    List<Motorista> getMotoristaByCnhCategoria(Categoria categoria);

    Motorista getMotoristaByCnhNumero(Long numero);
}
