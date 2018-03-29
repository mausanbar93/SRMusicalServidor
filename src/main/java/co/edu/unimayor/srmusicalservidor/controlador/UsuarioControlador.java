/**
 * UsuarioControlador.java
 *
 * Creada el 7/10/2017, 10:15:13 AM
 *
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 7/10/2017.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.controlador;

import co.edu.unimayor.srmusicalservidor.datos.dto.UsuarioDTO;
import co.edu.unimayor.srmusicalservidor.datos.util.ExtJSReturnUtil;
import co.edu.unimayor.srmusicalservidor.interfaz.JsonRequestMappingUtil;
import co.edu.unimayor.srmusicalservidor.servicio.UsuarioServicio;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 7/10/2017
 */
@Controller
@Configuration
@JsonRequestMappingUtil("/usuariocontrolador")
public class UsuarioControlador {

    private static final Log log = LogFactory.getLog(UsuarioControlador.class);

    @Autowired
    private UsuarioServicio usuarioservicio;

    @JsonRequestMappingUtil("listar")
    public @ResponseBody
    Map<String, Object> listarUsuario() {
        try {
            return ExtJSReturnUtil.mapOK(usuarioservicio.listar());
        } catch (Exception e) {
            log.error("Error listando usuarios", e);
            return ExtJSReturnUtil.mapError("error_listar");
        }
    }

    @JsonRequestMappingUtil("crear")
    public @ResponseBody
    Map<String, Object> agregarUsuario(@RequestBody List<UsuarioDTO> listausuarios) {
        try {
            List<UsuarioDTO> respuesta = usuarioservicio.crear(listausuarios);
            if (respuesta != null) {
                return ExtJSReturnUtil.mapOK(respuesta);
            } else {
                log.error("Username existente, por favor ingrese otro");
                return ExtJSReturnUtil.mapError("Username existente, por favor ingrese otro");
            }
        } catch (Exception e) {
            log.error("Error al crear usuario", e);
            return ExtJSReturnUtil.mapError("Error al crear usuario");
        }
    }

    @JsonRequestMappingUtil("actualizar")
    public @ResponseBody
    Map<String, Object> actualizarUsuario(@RequestBody List<UsuarioDTO> listausuarios) {
        try {
            List<UsuarioDTO> respuesta = usuarioservicio.actualizar(listausuarios);
            if (respuesta != null) {
                return ExtJSReturnUtil.mapOK(respuesta);
            } else {
                log.error("Username existente, por favor ingrese otro");
                return ExtJSReturnUtil.mapError("Username existente, por favor ingrese otro");
            }
        } catch (Exception e) {
            log.error("Error al editar usuario", e);
            return ExtJSReturnUtil.mapError("Error al editar usuario");
        }
    }

    @JsonRequestMappingUtil("eliminar")
    public @ResponseBody
    Map<String, Object> eliminarUsuario(@RequestBody List<UsuarioDTO> listausuarios) {
        try {
            usuarioservicio.eliminar(listausuarios);
            log.info("Exito eliminado usuarios");
            return ExtJSReturnUtil.mapOK("Exito eliminando usuarios");
        } catch (Exception e) {
            log.error("Error al eliminar usuario", e);
            return ExtJSReturnUtil.mapError("Error al eliminar usuario");
        }
    }

}
