/**
 * WebSecurityConfig.java
 *
 * Creada el 27/09/2017, 11:45:11 AM
 *
 * Clase Java desarrollada por Julián Andrés Bolaños Ortega para la empresa Seratic Ltda el día 27/09/2017.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras
 * envíar un email a <seratic@seratic.com> o a <julian.bolanos@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.seguridad;

/**
 *
 * @author Ing. Julián Andrés Bolaños Ortega <julian.bolanos@seratic.com>
 * @version 1.0
 * @date 27/09/2017
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
//                .antMatchers("/usuariocontroller/**").hasRole("LOGIN")
                //                .anyRequest().anonymous()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/error");
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

}
