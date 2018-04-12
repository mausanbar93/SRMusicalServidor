/**
 * CancionDTO.java
 *
 * Creada el 7/10/2017, 01:04:15 AM
 *
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 7/10/2017.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.datos.dto;

import co.edu.unimayor.srmusicalservidor.datos.Cancion;
import co.edu.unimayor.srmusicalservidor.datos.Estado;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 7/10/2017
 */
public class CancionDTO {

    private Integer id;
    private Integer estadoByEstadoApiRepustateId;
    private String estadoByEstadoApiRepustateNombre;
    private Integer estadoByEstadoApiTextProcessingId;
    private String estadoByEstadoApiTextProcessingNombre;
    private Integer estadoByEstadoApiSentiment140Id;
    private String estadoByEstadoApiSentiment140Nombre;
    private Integer estadoByEstadoApiSentimentId;
    private String estadoByEstadoApiSentimentNombre;
    private Integer estadoByEstadoReferenciaId;
    private String estadoByEstadoReferenciaNombre;
    private String artista;
    private String titulo;
    private String letra;
    private String api;
    private int rankingBillboard;
    private int semanaBillboard;
    private String url;
    private int numeroVisualizacion;
    private int duracionSegundo;
    private double valoracionReferencia;
    private boolean habilitado;

    public CancionDTO() {
    }

    public CancionDTO(Integer id, Integer estadoByEstadoApiRepustateId, String estadoByEstadoApiRepustateNombre, Integer estadoByEstadoApiTextProcessingId, String estadoByEstadoApiTextProcessingNombre, Integer estadoByEstadoApiSentiment140Id, String estadoByEstadoApiSentiment140Nombre, Integer estadoByEstadoApiSentimentId, String estadoByEstadoApiSentimentNombre, Integer estadoByEstadoReferenciaId, String estadoByEstadoReferenciaNombre, String artista, String titulo, String letra, String api, int rankingBillboard, int semanaBillboard, String url, int numeroVisualizacion, int duracionSegundo, double valoracionReferencia, boolean habilitado) {
        this.id = id;
        this.estadoByEstadoApiRepustateId = estadoByEstadoApiRepustateId;
        this.estadoByEstadoApiRepustateNombre = estadoByEstadoApiRepustateNombre;
        this.estadoByEstadoApiTextProcessingId = estadoByEstadoApiTextProcessingId;
        this.estadoByEstadoApiTextProcessingNombre = estadoByEstadoApiTextProcessingNombre;
        this.estadoByEstadoApiSentiment140Id = estadoByEstadoApiSentiment140Id;
        this.estadoByEstadoApiSentiment140Nombre = estadoByEstadoApiSentiment140Nombre;
        this.estadoByEstadoApiSentimentId = estadoByEstadoApiSentimentId;
        this.estadoByEstadoApiSentimentNombre = estadoByEstadoApiSentimentNombre;
        this.estadoByEstadoReferenciaId = estadoByEstadoReferenciaId;
        this.estadoByEstadoReferenciaNombre = estadoByEstadoReferenciaNombre;
        this.artista = artista;
        this.titulo = titulo;
        this.letra = letra;
        this.api = api;
        this.rankingBillboard = rankingBillboard;
        this.semanaBillboard = semanaBillboard;
        this.url = url;
        this.numeroVisualizacion = numeroVisualizacion;
        this.duracionSegundo = duracionSegundo;
        this.valoracionReferencia = valoracionReferencia;
        this.habilitado = habilitado;
    }

    public CancionDTO(Cancion cancion) {
        this.id = cancion.getId();
        Estado estadoRepustate = cancion.getEstadoByEstadoApiRepustate();
        this.estadoByEstadoApiRepustateId = estadoRepustate != null ? estadoRepustate.getId() : null;
        this.estadoByEstadoApiRepustateNombre = estadoRepustate != null ? estadoRepustate.getNombre() : null;
        Estado estadoTextProcessing = cancion.getEstadoByEstadoApiRepustate();
        this.estadoByEstadoApiTextProcessingId = estadoTextProcessing != null ? estadoTextProcessing.getId() : null;
        this.estadoByEstadoApiTextProcessingNombre = estadoTextProcessing != null ? estadoTextProcessing.getNombre() : null;
        Estado estadoSentiment140 = cancion.getEstadoByEstadoApiRepustate();
        this.estadoByEstadoApiSentiment140Id = estadoSentiment140 != null ? estadoSentiment140.getId() : null;
        this.estadoByEstadoApiSentiment140Nombre = estadoSentiment140 != null ? estadoSentiment140.getNombre() : null;
        Estado estadoSentiment = cancion.getEstadoByEstadoApiRepustate();
        this.estadoByEstadoApiSentimentId = estadoSentiment != null ? estadoSentiment.getId() : null;
        this.estadoByEstadoApiSentimentNombre = estadoSentiment != null ? estadoSentiment.getNombre() : null;
        Estado estadoReferencia = cancion.getEstadoByEstadoReferencia();
        this.estadoByEstadoReferenciaId = estadoReferencia != null ? estadoReferencia.getId() : null;
        this.estadoByEstadoReferenciaNombre = estadoReferencia != null ? estadoReferencia.getNombre() : null;
        this.artista = cancion.getArtista();
        this.titulo = cancion.getTitulo();
        this.letra = cancion.getLetra();
        this.api = cancion.getApi();
        this.rankingBillboard = cancion.getRankingBillboard();
        this.semanaBillboard = cancion.getSemanaBillboard();
        this.url = cancion.getUrl();
        this.numeroVisualizacion = cancion.getNumeroVisualizacion();
        this.duracionSegundo = cancion.getDuracionSegundo();
        this.valoracionReferencia = cancion.getValoracionReferencia();
        this.habilitado = cancion.isHabilitado();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstadoByEstadoApiRepustateId() {
        return estadoByEstadoApiRepustateId;
    }

    public void setEstadoByEstadoApiRepustateId(Integer estadoByEstadoApiRepustateId) {
        this.estadoByEstadoApiRepustateId = estadoByEstadoApiRepustateId;
    }

    public String getEstadoByEstadoApiRepustateNombre() {
        return estadoByEstadoApiRepustateNombre;
    }

    public void setEstadoByEstadoApiRepustateNombre(String estadoByEstadoApiRepustateNombre) {
        this.estadoByEstadoApiRepustateNombre = estadoByEstadoApiRepustateNombre;
    }

    public Integer getEstadoByEstadoApiTextProcessingId() {
        return estadoByEstadoApiTextProcessingId;
    }

    public void setEstadoByEstadoApiTextProcessingId(Integer estadoByEstadoApiTextProcessingId) {
        this.estadoByEstadoApiTextProcessingId = estadoByEstadoApiTextProcessingId;
    }

    public String getEstadoByEstadoApiTextProcessingNombre() {
        return estadoByEstadoApiTextProcessingNombre;
    }

    public void setEstadoByEstadoApiTextProcessingNombre(String estadoByEstadoApiTextProcessingNombre) {
        this.estadoByEstadoApiTextProcessingNombre = estadoByEstadoApiTextProcessingNombre;
    }

    public Integer getEstadoByEstadoApiSentiment140Id() {
        return estadoByEstadoApiSentiment140Id;
    }

    public void setEstadoByEstadoApiSentiment140Id(Integer estadoByEstadoApiSentiment140Id) {
        this.estadoByEstadoApiSentiment140Id = estadoByEstadoApiSentiment140Id;
    }

    public String getEstadoByEstadoApiSentiment140Nombre() {
        return estadoByEstadoApiSentiment140Nombre;
    }

    public void setEstadoByEstadoApiSentiment140Nombre(String estadoByEstadoApiSentiment140Nombre) {
        this.estadoByEstadoApiSentiment140Nombre = estadoByEstadoApiSentiment140Nombre;
    }

    public Integer getEstadoByEstadoApiSentimentId() {
        return estadoByEstadoApiSentimentId;
    }

    public void setEstadoByEstadoApiSentimentId(Integer estadoByEstadoApiSentimentId) {
        this.estadoByEstadoApiSentimentId = estadoByEstadoApiSentimentId;
    }

    public String getEstadoByEstadoApiSentimentNombre() {
        return estadoByEstadoApiSentimentNombre;
    }

    public void setEstadoByEstadoApiSentimentNombre(String estadoByEstadoApiSentimentNombre) {
        this.estadoByEstadoApiSentimentNombre = estadoByEstadoApiSentimentNombre;
    }

    public Integer getEstadoByEstadoReferenciaId() {
        return estadoByEstadoReferenciaId;
    }

    public void setEstadoByEstadoReferenciaId(Integer estadoByEstadoReferenciaId) {
        this.estadoByEstadoReferenciaId = estadoByEstadoReferenciaId;
    }

    public String getEstadoByEstadoReferenciaNombre() {
        return estadoByEstadoReferenciaNombre;
    }

    public void setEstadoByEstadoReferenciaNombre(String estadoByEstadoReferenciaNombre) {
        this.estadoByEstadoReferenciaNombre = estadoByEstadoReferenciaNombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getRankingBillboard() {
        return rankingBillboard;
    }

    public void setRankingBillboard(int rankingBillboard) {
        this.rankingBillboard = rankingBillboard;
    }

    public int getSemanaBillboard() {
        return semanaBillboard;
    }

    public void setSemanaBillboard(int semanaBillboard) {
        this.semanaBillboard = semanaBillboard;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumeroVisualizacion() {
        return numeroVisualizacion;
    }

    public void setNumeroVisualizacion(int numeroVisualizacion) {
        this.numeroVisualizacion = numeroVisualizacion;
    }

    public int getDuracionSegundo() {
        return duracionSegundo;
    }

    public void setDuracionSegundo(int duracionSegundo) {
        this.duracionSegundo = duracionSegundo;
    }

    public double getValoracionReferencia() {
        return valoracionReferencia;
    }

    public void setValoracionReferencia(double valoracionReferencia) {
        this.valoracionReferencia = valoracionReferencia;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
