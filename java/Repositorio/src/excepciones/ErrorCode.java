package excepciones;

public enum ErrorCode {
	USUARIO_NO_ENCONTRADO(1),
	CARTA_NO_ENCONTRADA(2),
	JUGADOR_NO_ENCONTRADO(3),
	JUEGO_NO_ENCONTRADO(4),
	BAZA_NO_ENCONTRADA(5),
	CATEGORIA_NO_ENCONTRADA(6),
	CHICO_NO_ENCONTRADO(7),
	COMUNICACION_ERROR(8),
	GRUPO_NO_ENCONTRADO(9),
	GRUPO_JUEGO_NO_ENCONTRADO(10),
	JUGADA_NO_ENCONTRADA(11),
	MANO_NO_ENCONTRADA(12),
	MIEMBRO_NO_ENCONTRADO(13),
	PAREJA_NO_ENCONTRADA(14);

	private final int codigo;
	private String descripcion;

	private ErrorCode(int codigo) {
	    this.codigo = codigo;
	}

	public String getDescripcion() {
	    return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return String.format("Error: %d | %s", codigo, descripcion);
	}
}
