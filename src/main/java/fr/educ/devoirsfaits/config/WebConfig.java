package fr.educ.devoirsfaits.config;

import fr.educ.devoirsfaits.controller.CorsFilter;
import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

import java.util.Arrays;

@Configurable
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UtilisateurService utilisateurService;



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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        //configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Max-Age",
                "Access-Control-Request-Headers", "Access-Control-Request-Method", "Authorization", "Cache-Control", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "Access-Control-Max-Age",
                "Access-Control-Request-Headers", "Access-Control-Request-Method", "Authorization", "Cache-Control", "Content-Type"));


        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    // this configuration allow the client app to access the this api
    // all the domain that consume this api must be included in the allowed o'rings
/*    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS");
            }
        };
    }*/


    // This method is for overriding some configuration of the WebSecurity
    // If you want to ignore some request or request patterns then you can
    // specify that inside this method
    /*@Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }*/

    // This method is used for override HttpSecurity of the web Application.
    // We can specify our authorization criteria inside this method.
    @Override
    protected void configure(HttpSecurity https) throws Exception {


        https.cors().and()
                // starts authorizing configurations
                .authorizeRequests()
                // ignoring the guest's urls "
                .antMatchers("/account/register","/account/login","/logout").permitAll()
                // authenticate all remaining URLS
                .antMatchers("/*").authenticated().and()
                /* "/logout" will log the user out by invalidating the HTTP Session,
                 * cleaning up any {link rememberMe()} authentication that was configured, */
                .logout()
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



    @SuppressWarnings("deprecation")
    @Bean
    public static MessageDigestPasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

}
