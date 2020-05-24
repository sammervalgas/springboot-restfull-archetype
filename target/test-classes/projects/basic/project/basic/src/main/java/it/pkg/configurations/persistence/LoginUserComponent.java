package it.pkg.configurations.persistence;

import it.pkg.model.autenticacao.LoginUser;
import it.pkg.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class LoginUserComponent {

    @Value("login.user")
    private String usuario;

    @Value("login.email")
    private String email;

    @Value("login.pass")
    private String senha;

    @Autowired
    private LoginUserRepository loginUserRepository;

    @PostConstruct
    public void persistSampleUser() {
        LoginUser user = new LoginUser(
                this.usuario,
                this.email,
                new BCryptPasswordEncoder().encode(this.senha));

        Optional<LoginUser> userOpt = loginUserRepository.findByEmail(user.getEmail());
        if(!userOpt.isPresent()) {
            loginUserRepository.save(user);
        }
    }
}
