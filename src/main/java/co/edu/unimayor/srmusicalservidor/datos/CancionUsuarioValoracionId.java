package co.edu.unimayor.srmusicalservidor.datos;
// Generated 29/03/2018 01:10:35 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CancionUsuarioValoracionId generated by hbm2java
 */
@Embeddable
public class CancionUsuarioValoracionId implements java.io.Serializable {

    private int cancion;
    private int usuario;

    public CancionUsuarioValoracionId() {
    }

    public CancionUsuarioValoracionId(int cancion, int usuario) {
        this.cancion = cancion;
        this.usuario = usuario;
    }

    @Column(name = "cancion", nullable = false)
    public int getCancion() {
        return this.cancion;
    }

    public void setCancion(int cancion) {
        this.cancion = cancion;
    }

    @Column(name = "usuario", nullable = false)
    public int getUsuario() {
        return this.usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof CancionUsuarioValoracionId)) {
            return false;
        }
        CancionUsuarioValoracionId castOther = (CancionUsuarioValoracionId) other;

        return (this.getCancion() == castOther.getCancion())
                && (this.getUsuario() == castOther.getUsuario());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getCancion();
        result = 37 * result + this.getUsuario();
        return result;
    }

}
