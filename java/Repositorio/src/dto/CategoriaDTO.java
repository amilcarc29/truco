package dto;

import java.io.Serializable;

public class CategoriaDTO extends JsonDTO  implements Serializable {

	private static final long serialVersionUID = -3374447334738410724L;

	private int idCategoria;
	private String nombre;
	private int minimoPartida;
	private int minimoPuntos;
	private int promedioMinimo;

	public CategoriaDTO(int idCategoria, String nombre, int minimoPartida, int minimoPuntos,
			int promedioMinimo) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		
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

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}
}
