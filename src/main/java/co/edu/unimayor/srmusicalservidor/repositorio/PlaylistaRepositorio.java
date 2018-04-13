/**
 * NativeQueryRepository.java
 * 
 * Creada el 28/12/2017, 12:02:50 PM
 * 
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 28/12/2017.
 * 
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras 
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */

package co.edu.unimayor.srmusicalservidor.repositorio;

import co.edu.unimayor.srmusicalservidor.datos.dto.PlaylistaDTO;
import java.util.List;

/**
 * 
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 28/12/2017
 */
public interface PlaylistaRepositorio {
    public List<PlaylistaDTO> listarCancionesUsuario(Integer usuario);
    public List<PlaylistaDTO> listarCancionesRecomendadas(List<Integer> lista);
}
