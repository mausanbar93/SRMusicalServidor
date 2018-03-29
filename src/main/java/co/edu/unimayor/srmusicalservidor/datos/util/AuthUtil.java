/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
 */
package co.edu.unimayor.srmusicalservidor.datos.util;

import co.edu.unimayor.srmusicalservidor.datos.dto.UsuarioDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Administrador
 */
public class AuthUtil {

    public static Authentication getCurrentAuth() {
        Authentication auth;
        try {
            auth = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            auth = null;
        }
        return auth;
    }

    public static UsuarioDTO getCurrentUser() {
        UsuarioDTO u;
        try {
            u = (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            u = null;
        }
        return u;
    }

    public static String getCurrentToken() {
        String t;
        try {
            t = SecurityContextHolder.getContext().getAuthentication().hashCode() + "";
        } catch (Exception e) {
            t = null;
        }
        return t;
    }
}
