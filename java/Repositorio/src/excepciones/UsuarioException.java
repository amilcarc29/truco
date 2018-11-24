package excepciones;

public class UsuarioException extends Exception {

	private static final long serialVersionUID = 3813900711380770054L;
	private static int error;

	public UsuarioException(String message) {
		super(message);
	}
	
	public UsuarioException(String message, int error) {
		super(message);
		UsuarioException.error = error;
	}
	
	public int getError() {
		return UsuarioException.error;
	}
}
