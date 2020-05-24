#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations.persistence;

import ${package}.model.autenticacao.LoginUser;
import ${package}.repository.LoginUserRepository;
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
