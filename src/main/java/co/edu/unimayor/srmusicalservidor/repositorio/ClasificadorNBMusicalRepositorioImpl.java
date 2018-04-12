/**
 * ClasificadorNBMusicalServicioImpl.java
 *
 * Creada el 5/04/2018, 08:51:02 PM
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 5/04/2018
 */
@Transactional
@Repository
public class ClasificadorNBMusicalRepositorioImpl implements ClasificadorNBMusicalRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PropiedadesMusicalesDTO> obtenerPropiedadesMusicalesCancionesValoradas(Integer usuario) {
        List<PropiedadesMusicalesDTO> retorno = em.unwrap(org.hibernate.Session.class)
                .createSQLQuery("SELECT\n"
                        + "  c.id AS id, c.ranking_billboard AS ranking, c.semana_billboard AS semanas, c.numero_visualizacion AS visualizacion, c.valoracion_referencia AS valoracion\n"
                        + "FROM cancion c\n"
                        + "  JOIN cancion_usuario_valoracion cuv ON cuv.cancion=c.id\n"
                        + "  WHERE cuv.usuario=:usuario\n"
                        + "  ORDER BY cuv.valoracion DESC")
                .setParameter("usuario", usuario)
                .setResultTransformer(org.hibernate.transform.Transformers.aliasToBean(PropiedadesMusicalesDTO.class))
                .list();
        return retorno;
    }

    @Override
    public List<PropiedadesMusicalesDTO> obtenerPropiedadesMusicalesCancionesNoValoradas(Integer usuario) {
        List<PropiedadesMusicalesDTO> retorno = em.unwrap(org.hibernate.Session.class)
                .createSQLQuery("SELECT\n"
                        + "  c.id AS id, c.ranking_billboard AS ranking, c.semana_billboard AS semanas, c.numero_visualizacion AS visualizacion, c.valoracion_referencia AS valoracion\n"
                        + "FROM cancion c\n"
                        + "WHERE c.id NOT IN (SELECT cuv.cancion FROM cancion_usuario_valoracion cuv WHERE cuv.usuario=:usuario ORDER BY cuv.valoracion DESC)")
                .setParameter("usuario", usuario)
                .setResultTransformer(org.hibernate.transform.Transformers.aliasToBean(PropiedadesMusicalesDTO.class))
                .list();
        return retorno;
    }

    @Override
    public List<PropiedadesMusicalesEstadoDTO> obtenerPropiedadesMusicalesCancionesEstadoValoradas(Integer usuario, String estado) {
        List<PropiedadesMusicalesEstadoDTO> retorno = em.unwrap(org.hibernate.Session.class)
                .createSQLQuery("SELECT\n"
                        + "  c.id AS id, c.ranking_billboard AS ranking, c.semana_billboard AS semanas, c.numero_visualizacion AS visualizacion, c.valoracion_referencia AS valoracion, e.nombre AS estado\n"
                        + "FROM cancion c\n"
                        + "  JOIN cancion_usuario_valoracion cuv ON cuv.cancion=c.id\n"
                        + "  JOIN estado e ON e.id=c.estado_referencia\n"
                        + "  WHERE cuv.usuario=:usuario AND e.nombre=:estado\n"
                        + "  ORDER BY cuv.valoracion DESC")
                .setParameter("usuario", usuario)
                .setParameter("estado", estado)
                .setResultTransformer(org.hibernate.transform.Transformers.aliasToBean(PropiedadesMusicalesEstadoDTO.class))
                .list();
        return retorno;
    }

    @Override
    public List<PropiedadesMusicalesEstadoDTO> obtenerPropiedadesMusicalesCancionesEstadoNoValoradas(Integer usuario, String estado) {
        List<PropiedadesMusicalesEstadoDTO> retorno = em.unwrap(org.hibernate.Session.class)
                .createSQLQuery("SELECT\n"
                        + "  c.id AS id, c.ranking_billboard AS ranking, c.semana_billboard AS semanas, c.numero_visualizacion AS visualizacion, c.valoracion_referencia AS valoracion, e.nombre AS estado\n"
                        + "FROM cancion c\n"
                        + "JOIN estado e ON e.id=c.estado_referencia\n"
                        + "WHERE c.id NOT IN (SELECT cuv.cancion FROM cancion_usuario_valoracion cuv WHERE cuv.usuario=:usuario ORDER BY cuv.valoracion DESC) AND e.nombre=:estado")
                .setParameter("usuario", usuario)
                .setParameter("estado", estado)
                .setResultTransformer(org.hibernate.transform.Transformers.aliasToBean(PropiedadesMusicalesEstadoDTO.class))
                .list();
        return retorno;
    }

}
