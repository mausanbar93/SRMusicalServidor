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
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

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

    @JsonRequestMappingUtil("leerTwitter")
    public @ResponseBody
    Map<String, Object> twitter() {
        Twitter twitter = new TwitterFactory().getInstance();
        // Twitter Consumer key & Consumer Secret
        twitter.setOAuthConsumer("MB1BUSegCKyzz0Zxf8QhaWpA5", "7FeuVqyGFt6qCJs8IBo59jQI6P3khaK4jVmq13PzFJxzeda7Op");
        // Twitter Access token & Access token Secret
        twitter.setOAuthAccessToken(new AccessToken("1837672465-eRV1Fu2cDfyexzpRilSYCwT7Y11dWP3xSsM8T1o",
                "rZnby1WIri9vtNnMwX1fUxmF8Zk2AQAob4aH5BGuxV4Iy"));
        try {
            // Getting Twitter Timeline using Twitter4j API
            ResponseList statusReponseList = twitter.getUserTimeline(new Paging(1, 5));
            for (Object item : statusReponseList) {
                log.info("Respuesta " + item.toString());
            }
            return ExtJSReturnUtil.mapOK(statusReponseList);
        } catch (TwitterException e) {
            log.error("Error en leerTwitter", e);
            return ExtJSReturnUtil.mapOK("Error en leerTwitter");
        }
    }
}
