/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.controlador;

import co.edu.unimayor.srmusicalservidor.datos.dto.UsuarioDTO;
import co.edu.unimayor.srmusicalservidor.datos.util.AuthUtil;
import co.edu.unimayor.srmusicalservidor.datos.util.ExtJSReturnUtil;
import co.edu.unimayor.srmusicalservidor.interfaz.JsonRequestMappingUtil;
import co.edu.unimayor.srmusicalservidor.seguridad.SeguridadServicio;
import co.edu.unimayor.srmusicalservidor.servicio.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * *@author Ing. Brayan Perafan
 */
@Controller
@Configuration
@EnableAsync
@EnableScheduling
@JsonRequestMappingUtil("/seguridadcontrolador")
public class SeguridadControlador {

    private static final Log log = LogFactory.getLog(SeguridadControlador.class);

    @Value("${constantes.ESTANDAR_TIEMPO}")
    private Integer ESTANDAR_TIEMPO;
    @Value("${constantes.TIEMPO_SESION}")
    private Integer TIEMPO_SESION;

    @Autowired
    private UsuarioServicio usuarioservicio;
    @Autowired
    protected SeguridadServicio seguridadServicio;

    @JsonRequestMappingUtil("login")
    public @ResponseBody
    Map<String, Object> validarLogin(@RequestParam("cuenta") String cuenta, @RequestParam("contrasena") String contrasena,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            List<UsuarioDTO> respuesta = usuarioservicio.login(cuenta, contrasena);
            request.getSession().setMaxInactiveInterval(172000);
            if (respuesta != null) {
                if (respuesta.get(0).isHabilitado()) {
                    Long tiempoSesion = new Long(ESTANDAR_TIEMPO * TIEMPO_SESION);
                    seguridadServicio.login(respuesta.get(0), tiempoSesion);
                    return ExtJSReturnUtil.mapOK(respuesta);
                } else {
                    log.error("Usuario inhabilitado");
                    return ExtJSReturnUtil.mapError("Usuario inhabilitado");
                }
            } else {
                log.error("Usuario no existente");
                return ExtJSReturnUtil.mapError("Usuario no existente");
            }
        } catch (Exception e) {
            log.error("Error ejecutando login", e);
            return ExtJSReturnUtil.mapError("Error ejecutando login");
        }
    }

    @JsonRequestMappingUtil(value = "/verificarSesion", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> verificarSesion() {
        Authentication auth = AuthUtil.getCurrentAuth();
        if (auth != null) {
            UsuarioDTO user = AuthUtil.getCurrentUser();
            if (user != null) {
                log.info("Existe sesion (USER): " + user.getCuenta());
                return ExtJSReturnUtil.mapOK(user);
            } else {
                List<String> respuesta = new ArrayList();
                respuesta.add("nosession");
                log.error("No existe sesion (SIN USER): " + auth.getPrincipal().toString());
                return ExtJSReturnUtil.mapOK(respuesta);
            }
        } else {
            List<String> respuesta = new ArrayList();
            respuesta.add("nosession");
            log.error("No existe sesion (SIN AUTH)");
            return ExtJSReturnUtil.mapOK(respuesta);
        }
    }

    @JsonRequestMappingUtil(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            seguridadServicio.logout(request, response);
            return new ModelAndView("redirect:/");
        } catch (Exception ex) {
            log.error("Error ejecutando logout", ex);
            return new ModelAndView("redirect:/");
        }
    }

}
