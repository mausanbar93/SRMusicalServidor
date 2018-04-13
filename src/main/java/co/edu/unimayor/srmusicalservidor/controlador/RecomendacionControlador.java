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

import co.edu.unimayor.srmusicalservidor.datos.dto.PrediccionDTO;
import co.edu.unimayor.srmusicalservidor.datos.util.ExtJSReturnUtil;
import co.edu.unimayor.srmusicalservidor.interfaz.JsonRequestMappingUtil;
import co.edu.unimayor.srmusicalservidor.servicio.ClasificadorNBMusicalServicio;
import co.edu.unimayor.srmusicalservidor.servicio.PlaylistaServicio;
import java.util.Map;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
@JsonRequestMappingUtil("/recomendacioncontrolador")
public class RecomendacionControlador {
    
    private static final Log log = LogFactory.getLog(RecomendacionControlador.class);
    
    @Autowired
    private final ClasificadorNBMusicalServicio clasificadorNBMusicalServicio = new ClasificadorNBMusicalServicio();
    @Autowired
    private PlaylistaServicio playlistaservicio;
    
    @JsonRequestMappingUtil("naivebayes_basico_normal")
    public @ResponseBody
    Map<String, Object> naivebayesBasicoNormal(@RequestParam("usuario") Integer usuario) throws Exception {
        try {
            clasificadorNBMusicalServicio.definirAtributos();
            clasificadorNBMusicalServicio.cargarConjuntoDatosBasico(usuario);
            clasificadorNBMusicalServicio.evaluarModelo();
            clasificadorNBMusicalServicio.obtenerResumen();
            clasificadorNBMusicalServicio.obtenerMatrizConfusion();
            ArrayList<PrediccionDTO> retorno = clasificadorNBMusicalServicio.obtenerPredicciones();
            if(retorno.size()>0){
                List<Integer> lista = retorno.stream().map(PrediccionDTO::getId_contenido).distinct().collect(Collectors.toList());
                return ExtJSReturnUtil.mapOK(playlistaservicio.listarCancionesRecomendadas(lista));
            }else{
                return ExtJSReturnUtil.mapError("Sin recomendacion de canciones");
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            log.error("Error ejecutando naivebayesBasicoNormal", e);
            return ExtJSReturnUtil.mapError("error_naivebayes_basico_normal");
        }
    }
    
    @JsonRequestMappingUtil("naivebayes_basico_positiva")
    public @ResponseBody
    Map<String, Object> naivebayesBasicoPositiva(@RequestParam("usuario") Integer usuario) throws Exception {
        try {
            clasificadorNBMusicalServicio.definirAtributos();
            clasificadorNBMusicalServicio.cargarConjuntoDatosBasico(usuario);
            clasificadorNBMusicalServicio.evaluarModelo();
            clasificadorNBMusicalServicio.obtenerResumen();
            clasificadorNBMusicalServicio.obtenerMatrizConfusion();
            ArrayList<PrediccionDTO> retorno = clasificadorNBMusicalServicio.obtenerPrediccionesPositivas();
            if(retorno.size()>0){
                List<Integer> lista = retorno.stream().map(PrediccionDTO::getId_contenido).distinct().collect(Collectors.toList());
                return ExtJSReturnUtil.mapOK(playlistaservicio.listarCancionesRecomendadas(lista));
            }else{
                return ExtJSReturnUtil.mapError("Sin recomendacion de canciones");
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            log.error("Error ejecutando naivebayesBasicoPositiva", e);
            return ExtJSReturnUtil.mapError("error_naivebayes_basico_positiva");
        }
    }
    
    @JsonRequestMappingUtil("naivebayes_intermedio_normal")
    public @ResponseBody
    Map<String, Object> naivebayesIntermedioNormal(@RequestParam("usuario") Integer usuario, @RequestParam("estado") String estado) throws Exception {
        try {
            clasificadorNBMusicalServicio.definirAtributos();
            clasificadorNBMusicalServicio.cargarConjuntoDatosIntermedio(usuario, estado);
            clasificadorNBMusicalServicio.evaluarModelo();
            clasificadorNBMusicalServicio.obtenerResumen();
            clasificadorNBMusicalServicio.obtenerMatrizConfusion();
            ArrayList<PrediccionDTO> retorno = clasificadorNBMusicalServicio.obtenerPredicciones();
            if(retorno.size()>0){
                List<Integer> lista = retorno.stream().map(PrediccionDTO::getId_contenido).distinct().collect(Collectors.toList());
                return ExtJSReturnUtil.mapOK(playlistaservicio.listarCancionesRecomendadas(lista));
            }else{
                return ExtJSReturnUtil.mapError("Sin recomendacion de canciones");
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            log.error("Error ejecutando naivebayesIntermedioNormal", e);
            return ExtJSReturnUtil.mapError("error_naivebayes_intermedio_normal");
        }
    }
    
    @JsonRequestMappingUtil("naivebayes_intermedio_positiva")
    public @ResponseBody
    Map<String, Object> naivebayesIntermedioPositiva(@RequestParam("usuario") Integer usuario, @RequestParam("estado") String estado) throws Exception {
        try {
            clasificadorNBMusicalServicio.definirAtributos();
            clasificadorNBMusicalServicio.cargarConjuntoDatosIntermedio(usuario, estado);
            clasificadorNBMusicalServicio.evaluarModelo();
            clasificadorNBMusicalServicio.obtenerResumen();
            clasificadorNBMusicalServicio.obtenerMatrizConfusion();
            ArrayList<PrediccionDTO> retorno = clasificadorNBMusicalServicio.obtenerPrediccionesPositivas();
            if(retorno.size()>0){
                List<Integer> lista = retorno.stream().map(PrediccionDTO::getId_contenido).distinct().collect(Collectors.toList());
                return ExtJSReturnUtil.mapOK(playlistaservicio.listarCancionesRecomendadas(lista));
            }else{
                return ExtJSReturnUtil.mapError("Sin recomendacion de canciones");
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            log.error("Error ejecutando naivebayesIntermedioPositiva", e);
            return ExtJSReturnUtil.mapError("error_naivebayes_intermedio_positiva");
        }
    }
    
}
