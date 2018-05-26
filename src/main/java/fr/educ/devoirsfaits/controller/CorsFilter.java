package fr.educ.devoirsfaits.controller;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@WebFilter(urlPatterns = "/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.addHeader("Access-Control-Allow-Origin" , "*");
        response.addHeader("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, "
                        + "Content-Type, Access-Control-Request-Method, "
                        + "Access-Control-Request-Headers, Authorization");
        response.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
        if (((HttpServletRequest)req).getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        chain.doFilter(req, response);
    }


    @Override
    public void destroy() {

    }

/*    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin" , "*");
        response.addHeader("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, "
                        + "Content-Type, Access-Control-Request-Method, "
                        + "Access-Control-Request-Headers, Authorization");
        response.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(request, response);
    }*/


}
