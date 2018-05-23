package fr.educ.devoirsfaits.config;

import fr.educ.devoirsfaits.security.JWTAuthenticationFilter;
import fr.educ.devoirsfaits.security.JWTAuthorizationFilter;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UtilisateurService utilisateurService;
    private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
    @Autowired
    private WebConfig webConfig;

    public WebConfig (UtilisateurService utilisateurService, MessageDigestPasswordEncoder messageDigestPasswordEncoder) {
        this.utilisateurService = utilisateurService;
        this.messageDigestPasswordEncoder = messageDigestPasswordEncoder;
    }


    // This method is for overriding the default AuthenticationManagerBuilder.
    // We can specify how the user details are kept in the application. It may
    // be in a database, LDAP or in memory.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(utilisateurService);

        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setPasswordAlreadyEncoded(true);
*/
        auth.userDetailsService(utilisateurService).passwordEncoder(this.passwordEncoder());

    }

    // this configuration allow the client app to access the this api
    // all the domain that consume this api must be included in the allowed o'rings
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");

            }
        };
    }
    // This method is for overriding some configuration of the WebSecurity
    // If you want to ignore some request or request patterns then you can
    // specify that inside this method
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    // This method is used for override HttpSecurity of the web Application.
    // We can specify our authorization criteria inside this method.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.cors().and().csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/account/login", "/account/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                // this disables session creation on Spring Security
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.cors().and()
                // starts authorizing configurations
                .authorizeRequests()
                // ignoring the guest's urls "
                .antMatchers("/account/register","/account/login","/logout").permitAll()
                // authenticate all remaining URLS
                //.antMatchers("/eleve/*").authenticated().and()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                //.addFilter(new JWTAuthorizationFilter(authenticationManager()))
                /* "/logout" will log the user out by invalidating the HTTP Session,
                 * cleaning up any {link rememberMe()} authentication that was configured, */
                .and().logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .and()
                // enabling the basic authentication
                .httpBasic().and()
                // configuring the session on the server
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();

    }


    //Permet les requetes depuis toutes les sources
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static MessageDigestPasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }


}
