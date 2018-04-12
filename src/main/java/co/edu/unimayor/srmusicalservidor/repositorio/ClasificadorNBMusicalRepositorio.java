/**
 * ClasificadorNBMusicalRepositorio.java
 * 
 * Creada el 5/04/2018, 08:50:50 PM
 * 
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 5/04/2018.
 * 
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras 
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */

package co.edu.unimayor.srmusicalservidor.repositorio;

import co.edu.unimayor.srmusicalservidor.datos.dto.PropiedadesMusicalesDTO;
import co.edu.unimayor.srmusicalservidor.datos.dto.PropiedadesMusicalesEstadoDTO;
import java.util.List;

/**
 * 
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 5/04/2018
 */
public interface ClasificadorNBMusicalRepositorio {
    public List<PropiedadesMusicalesDTO> obtenerPropiedadesMusicalesCancionesValoradas(Integer usuario);
    public List<PropiedadesMusicalesDTO> obtenerPropiedadesMusicalesCancionesNoValoradas(Integer usuario);
    
    public List<PropiedadesMusicalesEstadoDTO> obtenerPropiedadesMusicalesCancionesEstadoValoradas(Integer usuario, String estado);
    public List<PropiedadesMusicalesEstadoDTO> obtenerPropiedadesMusicalesCancionesEstadoNoValoradas(Integer usuario, String estado);

}