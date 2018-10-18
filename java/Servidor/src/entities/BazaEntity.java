package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Baza")
public class BazaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idBaza;

	@OneToOne
	@JoinColumn(name = "idMano")
	private ManoEntity mano;

	@OneToOne
	@JoinColumn(name = "idJugadaMayor", referencedColumnName = "idJugada")
	private JugadaEntity jugadaMayor;

	public BazaEntity() {
	}
}
