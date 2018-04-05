/**
 * LiquidacionRepository.java
 *
 * Creada el 5/12/2017, 10:26:06 AM
 *
 * Clase Java desarrollada por Mauricio S�nchez Barrag�n para la empresa Seratic Ltda el d�a 5/12/2017.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorizaci�n expl�cita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para informaci�n sobre el uso de esta clase, as� como bugs, actualizaciones o mejoras
 * env�ar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.repositorio;

import co.edu.unimayor.srmusicalservidor.datos.dto.PlaylistaDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Mauricio S�nchez Barrag�n <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 5/12/2017
 */
@Transactional
@Repository
public class PlaylistaRepositorioImpl implements PlaylistaRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PlaylistaDTO> listarCancionesUsuario(Integer usuario) {
        List<PlaylistaDTO> retorno = em.unwrap(org.hibernate.Session.class)
                .createSQLQuery("SELECT\n"
                        + "  c.id AS id, c.artista AS artista, c.titulo AS titulo, c.letra AS letra, c.duracion_segundo AS duracion, c.valoracion_referencia AS valoracion, e.nombre AS estado\n"
                        + "FROM cancion c\n"
                        + "  JOIN cancion_usuario_valoracion cuv ON cuv.cancion=c.id\n"
                        + "  JOIN estado e ON e.id=c.estado_referencia\n"
                        + "  WHERE cuv.usuario=:usuario")
                .setParameter("usuario", usuario)
                .setResultTransformer(org.hibernate.transform.Transformers.aliasToBean(PlaylistaDTO.class))
                .list();
        return retorno;
    }

}
