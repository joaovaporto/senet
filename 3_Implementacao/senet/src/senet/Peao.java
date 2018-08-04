package senet;

public class Peao {

	protected String cor;

	public Peao(String cor) {
		this.cor = cor;
	}
	
	public String obterCor() {
		return cor;
	}

	public void alterarCor(String cor) {
		throw new UnsupportedOperationException();
	}

}