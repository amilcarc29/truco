package negocio;

import dto.CategoriaDTO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public abstract class Categoria {

		private int idCategoria;
		private String nombre;
		private int score;
		private int minimoPartida;
		private int minimoPuntos;
		private int promedioMinimo;

		public Categoria(int idCategoria, String nombre, int score, int minimoPartida, int minimoPuntos,
				int promedioMinimo) {
			super();
			setIdCategoria(idCategoria);
			setNombre(nombre);
			setScore(score);
			setMinimoPartida(minimoPartida);
			setMinimoPuntos(minimoPuntos);
			setPromedioMinimo(promedioMinimo);
		}
		public Categoria(){
			
		}
		public abstract void actualizar(Usuario usuario) throws CategoriaException, UsuarioException;
		
		public abstract boolean debeSer(Usuario usuario) throws CategoriaException;
		
		private void setIdCategoria(int idCategoria) {
			this.idCategoria = idCategoria;
		}

		public int getIdCategoria() {
			return idCategoria;
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
	
		public CategoriaDTO toDTO() {
			return new CategoriaDTO(idCategoria, nombre, minimoPartida, minimoPuntos, promedioMinimo);
		}
}
