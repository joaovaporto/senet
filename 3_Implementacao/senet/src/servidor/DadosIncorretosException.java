package servidor;

public class DadosIncorretosException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DadosIncorretosException() {
		super("Dados inválidos.");
	}
}
