/**
 * PrediccionDTO.java
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

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 7/10/2017
 */
public class PrediccionDTO implements Comparable<PrediccionDTO> {

	private int id_contenido;
	private double prob0;
	private double prob1;
	private String pred;

	public int getId_contenido() {
		return id_contenido;
	}

	public void setId_contenido(int id_contenido) {
		this.id_contenido = id_contenido;
	}

	public double getProb0() {
		return prob0;
	}

	public void setProb0(double prob0) {
		this.prob0 = prob0;
	}

	public double getProb1() {
		return prob1;
	}

	public void setProb1(double prob1) {
		this.prob1 = prob1;
	}

	public String getPred() {
		return pred;
	}

	public void setPred(String pred) {
		this.pred = pred;
	}

        @Override
	public int compareTo(PrediccionDTO c) {
		// TODO Auto-generated method stub
		if (this.pred.equals("0")) {
			return new Double(this.prob0).compareTo(c.prob0);
		} else {
			return new Double(this.prob1).compareTo(c.prob1);
		}
	}
}