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
import co.edu.unimayor.srmusicalservidor.servicio.TwitterServicio;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 7/10/2017
 */
@Controller
@Configuration
@JsonRequestMappingUtil("/prueba")
public class PruebaControlador {

    private static final Log log = LogFactory.getLog(PruebaControlador.class);
    
    @Autowired
    private TwitterServicio twitterServicio;

    @JsonRequestMappingUtil("leerTwitter")
    public @ResponseBody
    Map<String, Object> twitter() {
        try {
            Twitter twitter = twitterServicio.autenticarTwitter();
            // Getting Twitter Timeline using Twitter4j API
            ResponseList statusReponseList = twitter.getUserTimeline(new Paging(1, 5));
            statusReponseList.forEach((item) -> {
                log.info("Respuesta " + item.toString());
            });
            return ExtJSReturnUtil.mapOK(statusReponseList);
        } catch (TwitterException e) {
            log.error("Error en leerTwitter", e);
            return ExtJSReturnUtil.mapOK("Error en leerTwitter");
        }
    }
}
