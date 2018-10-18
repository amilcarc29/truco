package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Carta")
public class CartaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCarta;

	private int numero;
	private String palo;
	private int pesoTruco;
	private int pesoEnvido;

	public CartaEntity(int numero, String palo, int pesoTruco, int pesoEnvido) {

		setNumero(numero);
		setPalo(palo);
		setPesoTruco(pesoTruco);
		setPesoEnvido(pesoEnvido);
	}

	public CartaEntity() {

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	public int getPesoTruco() {
		return pesoTruco;
	}

	public void setPesoTruco(int pesoTruco) {
		this.pesoTruco = pesoTruco;
	}

	public int getPesoEnvido() {
		return pesoEnvido;
	}

	public void setPesoEnvido(int pesoEnvido) {
		this.pesoEnvido = pesoEnvido;
	}
}
