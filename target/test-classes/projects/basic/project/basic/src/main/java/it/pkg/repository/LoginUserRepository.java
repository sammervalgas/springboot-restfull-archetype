package it.pkg.repository;

import it.pkg.model.autenticacao.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

    Optional<LoginUser> findByEmail(String email);

    Optional<LoginUser> findByName(String name);
}
