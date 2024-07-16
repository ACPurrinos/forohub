package com.acpurrinos.forohub.security;

import com.acpurrinos.forohub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  @Autowired
  private TokenService tokenService;

    @Autowired
   private UsuarioRepository usuarioRepository;

    //Obtener el token del header
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader=request.getHeader("Authorization");//.replace("Bearer ", "");
        if(authHeader!=null){
            var token = authHeader.replace("Bearer ", "");
           // System.out.println(token);
            //System.out.println(tokenService.getSubject(token));
            var subject = tokenService.getSubject(token);
            if (subject != null){
                //token valido
                var usuario =usuarioRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //forzamos el inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);}



       /* //Get the Token
        String authHeader = request.getHeader("Authorization");
        String subject = "";

        if(authHeader!= null)
        {
            var token =  authHeader.replace("Bearer ", "");

            System.out.println(token);

            subject = tokenService.getSubject(token);

            System.out.println(tokenService.getSubject(token));

            if(subject != null)
            {
                var user = usersRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
*/

    }