package it.pkg.repository;

import it.pkg.enums.Categoria;
import it.pkg.model.motorista.Motorista;
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
