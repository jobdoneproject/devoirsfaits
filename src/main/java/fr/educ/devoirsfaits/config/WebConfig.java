package fr.educ.devoirsfaits.config;

import fr.educ.devoirsfaits.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

    // this configuration allow the client app to access the this api
    // all the domain that consume this api must be included in the allowed o'rings
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // return new WebMvcConfigurerAdapter() {
        //     @Override
        //     public void addCorsMappings(CorsRegistry registry) {
        //         registry.addMapping("/**").allowedOrigins("http://localhost:4200")
        //         .allowedOrigins("http://localhost:8080")
        //         .allowedOrigins("http://206.189.126.80").allowedHeaders("*").allowedMethods("*")
        //         .allowedOrigins("https://app-f4ff65e9-499f-4997-b3f3-15b3f90cc4c9.cleverapps.io").allowedHeaders("*").allowedMethods("*").allowCredentials(true)
        //         .allowedOrigins("https://devoirsfaits-demo.cleverapps.io").allowedHeaders("*").allowedMethods("*").allowCredentials(true)
        //         .allowedOrigins("http://app-f4ff65e9-499f-4997-b3f3-15b3f90cc4c9.cleverapps.io").allowedHeaders("*").allowedMethods("*").allowCredentials(true)
        //         .allowedOrigins("http://devoirsfaits-demo.cleverapps.io").allowedHeaders("*").allowedMethods("*").allowCredentials(true);
        //     }
        // };
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true)
                .allowedOrigins("http://localhost:8080").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true)
                .allowedOrigins("http://206.189.126.80").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true)
                .allowedOrigins("https://app-f4ff65e9-499f-4997-b3f3-15b3f90cc4c9.cleverapps.io").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true)
                .allowedOrigins("https://devoirsfaits-demo.cleverapps.io").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true)
                .allowedOrigins("http://app-f4ff65e9-499f-4997-b3f3-15b3f90cc4c9.cleverapps.io").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true)
                .allowedOrigins("http://devoirsfaits-demo.cleverapps.io").allowedHeaders("X-Auth-Token", "Content-Type", "X-Requested-With", "Accept", "Origin", "Authorization", "Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("*").allowCredentials(true);
            }
        };

        // return new WebMvcConfigurerAdapter() {
        //     @Override
        //     public void addCorsMappings(CorsRegistry registry) {
        //         registry.addMapping("/**")
        //         .allowedOrigins("http://localhost:4200")
        //         .allowedOrigins("http://localhost:8080")
        //         .allowedOrigins("http://206.189.126.80")
        //         .allowedOrigins("http://app-f4ff65e9-499f-4997-b3f3-15b3f90cc4c9.cleverapps.io")
        //         .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
        //         .allowedHeaders("Content-Type", "X-Requested-With", "Accept", "Origin", "Authentication", "Access-Control-Request-Method","Access-Control-Request-Headers")
        //         .exposedHeaders("Access-Control-Allow-Origin")                
        //         .allowedOrigins("http://devoirsfaits-demo.cleverapps.io")
        //         .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
        //         .allowedHeaders("Content-Type", "X-Requested-With", "1ccept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
        //         .exposedHeaders("Access-Control-Allow-Origin")                
        //         .allowedOrigins("https://app-f4ff65e9-499f-4997-b3f3-15b3f90cc4c9.cleverapps.io")
        //         .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
        //         .allowedHeaders("Content-Type", "X-Requested-With", "1ccept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
        //         .exposedHeaders("Access-Control-Allow-Origin")
        //         .allowedOrigins("https://devoirsfaits-demo.cleverapps.io")
        //         .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
        //         .allowedHeaders("Content-Type", "X-Requested-With", "Accept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
        //         .exposedHeaders("Access-Control-Allow-Origin");
        //     }
        // };        
    }



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
        //Required for SSL?
        // See https://stackoverflow.com/questions/37835342/spring-security-ajax-login-use-http-redirect-instead-of-https
        //https.requiresChannel().antMatchers("/account/register","/account/login","/logout").requiresSecure();

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

    //Rajout RL 201807
    //https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
    // @Override
    // public void configure(WebSecurity web) throws Exception {
    //     web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    // }

    @SuppressWarnings("deprecation")
    @Bean
    public static MessageDigestPasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

}
