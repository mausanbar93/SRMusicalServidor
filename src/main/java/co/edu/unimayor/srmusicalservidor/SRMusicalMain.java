/**
 * SRMusicalMain.java
 * 
 * Creada el 29/03/2018, 12:57:09 PM
 * 
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 29/03/2018.
 * 
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras 
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */

package co.edu.unimayor.srmusicalservidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 29/03/2018
 */
@EnableAutoConfiguration
@SpringBootApplication
public class SRMusicalMain extends SpringBootServletInitializer{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SpringApplication.run(SRMusicalMain.class, args);
    }

}
