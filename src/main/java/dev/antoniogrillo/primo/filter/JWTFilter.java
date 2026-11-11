package dev.antoniogrillo.primo.filter;

import dev.antoniogrillo.primo.entity.Persona;
import dev.antoniogrillo.primo.facade.def.JwtFilterFacade;
import dev.antoniogrillo.primo.service.def.JWTService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JwtFilterFacade facade;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if(SecurityContextHolder.getContext().getAuthentication()==null){
            String token=request.getHeader("Authorization");
            if(token!=null && token.startsWith("Bearer ")){
                token=token.substring(7);
                try{
                    Persona p=facade.decodeToken(token);
                    if(p==null)throw new JwtException("Invalid token");
                    UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(p,null,p.getAuthorities());
                    WebAuthenticationDetails w=new WebAuthenticationDetailsSource().buildDetails(request);
                    upat.setDetails(w);
                    SecurityContextHolder.getContext().setAuthentication(upat);

                }catch (JwtException e){
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        }
        chain.doFilter(request,response);
    }
}
