#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations.security;


import ${package}.model.autenticacao.LoginUser;
import ${package}.model.login.TokenDTO;
import ${package}.repository.LoginUserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;
    private LoginUserRepository loginUserRepository;

    public AuthenticationTokenFilter(JwtTokenService jwtTokenService, LoginUserRepository loginUserRepository) {
        this.jwtTokenService = jwtTokenService;
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoveryToken(request);
        boolean isTokenGranted = jwtTokenService.isTokenGranted(token);

        if (isTokenGranted) {
            authenticateClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long idUser = jwtTokenService.getUserId(token);
        Optional<LoginUser> loginUserOpt = loginUserRepository.findById(idUser);
        if (loginUserOpt.isPresent()) {
            LoginUser loginUser = loginUserOpt.get();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getProfiles());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String method = TokenDTO.AuthMethod.BEARER.getName();

        if (token == null ||
                token.isEmpty() ||
                    !token.startsWith(method)) {
            return null;
        }
        return token.replace(method.trim(), "");
    }
}
