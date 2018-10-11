package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias")
public class CategoriaEntity {

	@Id
	private int idCategoria;
	private String nombre;
	private int score;
	private int minimoPartida;
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
		this.minimoPartida = minimoPartida;
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

	public int getMinimoPartida() {
		return minimoPartida;
	}

	public void setMinimoPartida(int minimoPartida) {
		this.minimoPartida = minimoPartida;
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
