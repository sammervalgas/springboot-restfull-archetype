package it.pkg.configurations.security;

import it.pkg.repository.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Value("${auth.jwt.use_by_default}")
    private boolean isJWTAuthentication;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * Autenticação.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        if(isJWTAuthentication) {
            auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
        }
    }

    /**
     * Autorização para url HTTP.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = http.authorizeRequests()
                .antMatchers(HttpMethod.GET, Pattern.GET.paths ).permitAll()
                .antMatchers(HttpMethod.POST, Pattern.POST.paths).permitAll()
                .anyRequest()
                .authenticated();

        if(isJWTAuthentication) {
            registry.and().csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().addFilterBefore(new AuthenticationTokenFilter(jwtTokenService, loginUserRepository), UsernamePasswordAuthenticationFilter.class);
        } else {
           registry.and().formLogin();
        }
    }

    /**
     * Permissao de acesso a arquivos.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/**.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**");
    }

    private static enum Pattern {
        GET("/", "/actuator/health", "/categorias", "/categorias/*"),

        POST("/auth"),

        DELETE(),

        PUT();

        private String[] paths;

        Pattern(String ... paths) {
            this.paths = paths;
        }

        public String[] paths() {
            return paths;
        }
    }
}
