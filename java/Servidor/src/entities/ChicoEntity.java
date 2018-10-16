package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Chico")
public class ChicoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idChico;
	
	@OneToOne
	@JoinColumn(name = "idJuego")
	private JuegoEntity juego;
	
	@OneToOne
	@JoinColumn(name = "idParejaGanadora", referencedColumnName = "idPareja")
	private ParejaEntity parejaGanadora;
	
	private int puntosXGanar;

	public ChicoEntity(JuegoEntity juego, ParejaEntity parejaGanadora, int puntosXGanar) {
		super();
		this.juego = juego;
		this.parejaGanadora = parejaGanadora;
		this.puntosXGanar = puntosXGanar;
	}

	public Integer getIdChico() {
		return idChico;
	}

	public void setIdChico(Integer idChico) {
		this.idChico = idChico;
	}

	public JuegoEntity getJuego() {
		return juego;
	}

	public void setJuego(JuegoEntity juego) {
		this.juego = juego;
	}

	public ParejaEntity getParejaGanadora() {
		return parejaGanadora;
	}

	public void setParejaGanadora(ParejaEntity parejaGanadora) {
		this.parejaGanadora = parejaGanadora;
	}

	public int getPuntosXGanar() {
		return puntosXGanar;
	}

	public void setPuntosXGanar(int puntosXGanar) {
		this.puntosXGanar = puntosXGanar;
	}
	
	
	

}
