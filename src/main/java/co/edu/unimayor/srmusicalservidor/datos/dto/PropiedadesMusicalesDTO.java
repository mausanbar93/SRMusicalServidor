/**
 * PropiedadesMusicalesDTO.java
 *
 * Creada el 5/04/2018, 09:43:11 PM
 *
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 5/04/2018.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.datos.dto;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 5/04/2018
 */
public class PropiedadesMusicalesDTO {

    private Integer id;
    private Integer ranking;
    private Integer semanas;
    private Integer visualizacion;
    private Double valoracion;

    public PropiedadesMusicalesDTO() {
    }

    public PropiedadesMusicalesDTO(Integer id, Integer ranking, Integer semanas, Integer visualizacion, Double valoracion) {
        this.id = id;
        this.ranking = ranking;
        this.semanas = semanas;
        this.visualizacion = visualizacion;
        this.valoracion = valoracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getSemanas() {
        return semanas;
    }

    public void setSemanas(Integer semanas) {
        this.semanas = semanas;
    }

    public Integer getVisualizacion() {
        return visualizacion;
    }

    public void setVisualizacion(Integer visualizacion) {
        this.visualizacion = visualizacion;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

}
