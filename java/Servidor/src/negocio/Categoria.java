package negocio;

public abstract class Categoria {
				
		private int idCategoria;
		private String nombre;
		private int score;
		private int minimoPartida;
		private int minimoPuntos;
		private int promedioMinimo;
		
		public  abstract void actualizar();
		
		
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
	
		
		
			
}
