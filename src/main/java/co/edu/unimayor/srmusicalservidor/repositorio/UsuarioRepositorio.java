/**
 * UsuarioRepositorio.java
 *
 * Creada el 23/09/2017, 01:21:23 PM
 *
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 23/09/2017.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.repositorio;

import co.edu.unimayor.srmusicalservidor.datos.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 23/09/2017
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT u.* FROM Usuario u WHERE u.cuenta = :cuenta AND u.contrasena = :contrasena", nativeQuery = true)
    public List<Usuario> login(@Param("cuenta") String username, @Param("contrasena") String password);

    @Query(value = "SELECT u.* FROM Usuario u WHERE u.cuenta = :cuenta AND u.habilitado = TRUE", nativeQuery = true)
    public Usuario findCuenta(@Param("cuenta") String username);

}
