package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

	@Id
	private int idCategoria;
	private String nombre;
	private int score;
	private int minimoPartidas;
	private int minimoPuntos;
	private int promedioMinimo;

	public CategoriaEntity() {
	}

	public CategoriaEntity(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos,
			int promedioMinimo) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.score = score;
		this.minimoPartidas = minimoPartida;
		this.minimoPuntos = minimoPuntos;
		this.promedioMinimo = promedioMinimo;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMinimoPartidas() {
		return minimoPartidas;
	}

	public void setMinimoPartidas(int minimoPartidas) {
		this.minimoPartidas = minimoPartidas;
	}

	public int getMinimoPartida() {
		return minimoPartidas;
	}

	public void setMinimoPartida(int minimoPartida) {
		this.minimoPartidas = minimoPartida;
	}

	public int getMinimoPuntos() {
		return minimoPuntos;
	}

	public void setMinimoPuntos(int minimoPuntos) {
		this.minimoPuntos = minimoPuntos;
	}

	public int getPromedioMinimo() {
		return promedioMinimo;
	}

	public void setPromedioMinimo(int promedioMinimo) {
		this.promedioMinimo = promedioMinimo;
	}
}
