package it.pkg.repository;

import it.pkg.model.carro.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    Page<Carro> findCarrosByMotoristaId(Long idMotorista, Pageable paginacao);

    Optional<Carro> findByPlaca(String placa);

}
