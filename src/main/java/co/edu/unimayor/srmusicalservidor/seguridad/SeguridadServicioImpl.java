/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.seguridad;

import co.edu.unimayor.srmusicalservidor.datos.dto.UsuarioDTO;
import co.edu.unimayor.srmusicalservidor.datos.util.AuthUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrador
 */
@Service
public class SeguridadServicioImpl implements SeguridadServicio {

    private static final Logger log = LoggerFactory.getLogger(SeguridadServicioImpl.class);

    @Override
    public void login(UsuarioDTO usuario, Long tiempoSesion) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_LOGIN"));
        if (tiempoSesion == null || tiempoSesion == 0) {
            tiempoSesion = new Long(60 * 24 * 2);
            tiempoSesion = tiempoSesion * 1000;
        }        
        Date tokenExpiration = new Date();
        tokenExpiration.setTime(tokenExpiration.getTime() + tiempoSesion); // Tiempo sesion corta
        ExpiringUsernameAuthenticationToken token = new ExpiringUsernameAuthenticationToken(tokenExpiration, usuario, usuario.getContrasena(), grantedAuthorities);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            System.out.println("Token creado, expirará " + token.getTokenExpiration());
        }
    }

    @Override
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = AuthUtil.getCurrentAuth();
        if (auth != null) {
            try {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return Boolean.TRUE;
            } catch (Exception e) {
                return Boolean.FALSE;
            }
        } else {
            return Boolean.FALSE;
        }
    }
}
