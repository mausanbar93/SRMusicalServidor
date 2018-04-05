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

import co.edu.unimayor.srmusicalservidor.datos.util.ExtJSReturnUtil;
import co.edu.unimayor.srmusicalservidor.interfaz.JsonRequestMappingUtil;
import co.edu.unimayor.srmusicalservidor.servicio.PlaylistaServicio;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 7/10/2017
 */
@Controller
@Configuration
@JsonRequestMappingUtil("/playlistacontrolador")
public class PlaylistaControlador {

    private static final Log log = LogFactory.getLog(PlaylistaControlador.class);

    @Autowired
    private PlaylistaServicio playlistaservicio;

    @JsonRequestMappingUtil("listar_canciones_usuario")
    public @ResponseBody
    Map<String, Object> listarCancionesUsuario(@RequestParam("usuario") Integer usuario) {
        try {
            return ExtJSReturnUtil.mapOK(playlistaservicio.listarCancionesUsuario(usuario));
        } catch (Exception e) {
            log.error("Error listando canciones", e);
            return ExtJSReturnUtil.mapError("error_listar");
        }
    }

//    @JsonRequestMappingUtil("actualizar_canciones_usuario")
//    public @ResponseBody
//    Map<String, Object> actualizarUsuario(@RequestBody List<UsuarioDTO> listausuarios) {
//        try {
//            List<UsuarioDTO> respuesta = usuarioservicio.actualizar(listausuarios);
//            if (respuesta != null) {
//                return ExtJSReturnUtil.mapOK(respuesta);
//            } else {
//                log.error("Username existente, por favor ingrese otro");
//                return ExtJSReturnUtil.mapError("Username existente, por favor ingrese otro");
//            }
//        } catch (Exception e) {
//            log.error("Error al editar usuario", e);
//            return ExtJSReturnUtil.mapError("Error al editar usuario");
//        }
//    }
}
