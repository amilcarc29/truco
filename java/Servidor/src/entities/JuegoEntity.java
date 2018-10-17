package entities;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import negocio.Pareja;

@Entity
@Table(name = "Juego")
public class JuegoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idJuego;

	@OneToOne
	@JoinColumn(name = "idPareja1")
	private ParejaEntity pareja1;

	@OneToOne
	@JoinColumn(name = "idPareja2")
	private ParejaEntity pareja2;

	@OneToOne
	@JoinColumn(name = "idParejaGanadora")
	private ParejaEntity parejaGanadora;

	private String tipoDeJuego;

	private Date fecha;

	private boolean activo;

	public JuegoEntity() {

	}
	public int getId() {

		return this.idJuego;
	}

	public ParejaEntity getPareja1() {

		return this.pareja1;
	}
	
	public ParejaEntity getPareja2() {

		return this.pareja2;
	}

	public JuegoEntity(ParejaEntity pareja1, ParejaEntity pareja2, String tipoDeJuego) {
		super();
		this.pareja1 = pareja1;
		this.pareja2 = pareja2;
		this.setTipoDeJuego(tipoDeJuego);
		this.setActivo(true);
		this.setFecha(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

	}

	public String getTipoDeJuego() {
		return tipoDeJuego;
	}

	public void setTipoDeJuego(String tipoDeJuego) {
		this.tipoDeJuego = tipoDeJuego;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
