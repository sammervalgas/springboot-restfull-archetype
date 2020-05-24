#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations.security;

import ${package}.model.autenticacao.LoginUser;
import ${package}.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private LoginUserRepository userRepository;

    /**
     * Utilizamos o email como chave de acesso.
     * @param email
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<LoginUser> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Dados de login inválidos!");
    }
}
