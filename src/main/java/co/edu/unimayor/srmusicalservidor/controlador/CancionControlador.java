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

import co.edu.unimayor.srmusicalservidor.datos.dto.CancionDTO;
import co.edu.unimayor.srmusicalservidor.datos.util.ExtJSReturnUtil;
import co.edu.unimayor.srmusicalservidor.interfaz.JsonRequestMappingUtil;
import co.edu.unimayor.srmusicalservidor.servicio.CancionServicio;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
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
@JsonRequestMappingUtil("/cancioncontrolador")
public class CancionControlador {

    private static final Log log = LogFactory.getLog(CancionControlador.class);

    @Autowired
    private CancionServicio cancionservicio;

    @JsonRequestMappingUtil("leerJSON")
    public @ResponseBody
    Map<String, Object> leerJSON() {
        try {
            cancionservicio.leer();
            return ExtJSReturnUtil.mapOk("Exito leyendo JSON");
        } catch (JsonIOException | JsonSyntaxException e) {
            log.error("Error leyendo JSON", e);
            return ExtJSReturnUtil.mapError("error_json");
        }
    }

    @JsonRequestMappingUtil("listar")
    public @ResponseBody
    Map<String, Object> listarCancion() {
        try {
            return ExtJSReturnUtil.mapOK(cancionservicio.listar());
        } catch (Exception e) {
            log.error("Error listando canciones", e);
            return ExtJSReturnUtil.mapError("error_listar");
        }
    }

//    @JsonRequestMappingUtil("crear")
//    public @ResponseBody
//    Map<String, Object> agregarUsuario(@RequestBody List<UsuarioDTO> listausuarios) {
//        try {
//            List<UsuarioDTO> respuesta = usuarioservicio.crear(listausuarios);
//            if (respuesta != null) {
//                return ExtJSReturnUtil.mapOK(respuesta);
//            } else {
//                log.error("Username existente, por favor ingrese otro");
//                return ExtJSReturnUtil.mapError("Username existente, por favor ingrese otro");
//            }
//        } catch (Exception e) {
//            log.error("Error al crear usuario", e);
//            return ExtJSReturnUtil.mapError("Error al crear usuario");
//        }
//    }
//
//    @JsonRequestMappingUtil("actualizar")
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
    @JsonRequestMappingUtil("eliminar")
    public @ResponseBody
    Map<String, Object> eliminarCancion(@RequestBody List<CancionDTO> listacanciones) {
        try {
            cancionservicio.eliminar(listacanciones);
            log.info("Exito eliminado canciones");
            return ExtJSReturnUtil.mapOK("Exito eliminando canciones");
        } catch (Exception e) {
            log.error("Error al eliminar cancion", e);
            return ExtJSReturnUtil.mapError("Error al eliminar cancion");
        }
    }

}
