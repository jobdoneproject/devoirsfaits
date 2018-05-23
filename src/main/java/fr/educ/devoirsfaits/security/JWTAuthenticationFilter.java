package fr.educ.devoirsfaits.security;

import com.fasterxml.jackson.core.JsonParser;
import fr.educ.devoirsfaits.model.Utilisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static fr.educ.devoirsfaits.security.SecurityConstants.EXPIRATION_TIME;
import static fr.educ.devoirsfaits.security.SecurityConstants.HEADER_STRING;
import static fr.educ.devoirsfaits.security.SecurityConstants.SECRET;
import static fr.educ.devoirsfaits.security.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/account/login"); //Cette ligne définit l'url account/login en par défaut pour le login
        //setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/eleve", "POST"));
    }

    //Recup les logs (user + pwd) et les envoit à AuthenticationManager
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            Utilisateur creds = objectMapper.readValue(req.getInputStream(), Utilisateur.class);setFilterProcessesUrl("/account/login");

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),                //Même si log via mail la fonction getUsername de Utilisateur renvoit ce mail
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Cette méthode est appelée suite à un login OK, génère le token pour l'utilisateur
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}