package dto;



public class UsuarioDTO {

	private int idUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntaje;
	private String apodo;
	private String pass;
	private String email;
	//private CategoriaDTO categoria;
	private boolean activo;

	public UsuarioDTO(int idUsuario, int partidasGanadas, int partidasPerdidas, int puntaje, String apodo, String pass,
			String email, boolean activo) {//, CategoriaDTO categoria
		super();
		this.idUsuario = idUsuario;
		this.partidasGanadas = partidasGanadas;
		this.partidasPerdidas = partidasPerdidas;
		this.puntaje = puntaje;
		this.apodo = apodo;
		this.pass = pass;
		this.email = email;
		//this.categoria = categoria;
		this.activo = activo;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}

	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	/*public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}*/

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
