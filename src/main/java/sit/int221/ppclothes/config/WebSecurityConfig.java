package sit.int221.ppclothes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService);
        //.passwordEncoder(passwordEncoder());
    }
    @Value("#{'${clothes.origin.method}'.split(',')}")
    private String[] methodList;
    @Value("#{'${clothes.origin.host}'.split(',')}")
    String[] hostList;
    @Value("#{'${clothes.origin.headers}'.split(',')}")
    String[] headersList;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.cors(
                        config -> {
                            CorsConfiguration cors = new CorsConfiguration();
                            cors.setAllowCredentials(true);
                            cors.setAllowedOrigins(Arrays.asList(hostList));
                            cors.setAllowedMethods(Arrays.asList(methodList));
                            cors.setAllowedHeaders(Arrays.asList(headersList));

                            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                            source.registerCorsConfiguration("/**", cors);

                            config.configurationSource(source);
                        }
                ).csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests()
                .antMatchers("/main/**").permitAll()
                .antMatchers("/member/**").hasRole("MEMBER")
                .antMatchers("/staff/**").hasRole("STAFF")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/adsta/**").hasAnyRole("ADMIN","STAFF")
                .antMatchers("/admem/**").hasAnyRole("ADMIN","MEMBER")
                .antMatchers("/allroles/**").hasAnyRole("ADMIN","STAFF","MEMBER").
                // all other requests need to be authenticated
                        and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}