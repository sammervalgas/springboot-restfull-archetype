package it.pkg.model.login;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * A LoginForm está escrita com Lombok Abstraction:
 *
 * @Data insere Getters e Setter e construtor Default.
 * @NoArgsConstructor corrige erro com jpa em caso e entidades.
 *
 */
public @NoArgsConstructor @Data class LoginForm {

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty
    private String pass;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, pass);
    }
}
