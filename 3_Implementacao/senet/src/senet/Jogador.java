package senet;

//import java.util.Random;

public class Jogador {

	protected String nome;
	protected boolean daVez;
	protected String corPeao;
	protected boolean lancouGravetos;
	protected int numeroCasaPeaoLiberacao;
	protected int pontuacao;
	protected boolean vencedor;
	protected int qtdPeoesLiberados;

	public Jogador(String nome) {
		this.nome = nome;
		daVez = false;
		corPeao = null;
		lancouGravetos = false;
		numeroCasaPeaoLiberacao = 0;
		pontuacao = 0;
		vencedor = false;
		qtdPeoesLiberados = 0;
	}

	public int sortearPontuacao() {
		/*Random gerador;
		int pontuacao;
		
		gerador = new Random();
		pontuacao = gerador.nextInt(5) + 1;
		alterarPontuacao(pontuacao);*/
		
		GeradorAleatoriedade gerador;
		int pontuacao;
		
		gerador = new GeradorAleatoriedade();
		pontuacao = gerador.obterInteiroAleatorio(5) + 1;
		alterarPontuacao(pontuacao);
		
		return pontuacao;
	}

	public boolean ehSuaCorPeao(String cor) {
		return corPeao.equals(cor);
	}

	public boolean avaliarPeoesDefinidos() {
		if (corPeao != null)
			return true;
		
		return false;
	}

	public String obterNomeJogador() {
		return nome;
	}

	public String obterCorPeao() {
		return corPeao;
	}

	public boolean obterJogadorDaVez() {
		return daVez;
	}
	
	public boolean obterVencedor() {
		return vencedor;
	}
	public int obterPontuacao() {
		return pontuacao;
	}

	public boolean obterLancouGravetos() {
		return lancouGravetos;
	}

	/**
	 * 
	 * @param nome_Jogador
	 */
	public void alterarNomeJogador(String nome) {
		this.nome = nome;
	}

	public void alterarJogadorDaVez() {
		daVez = !daVez;
	}

	public void alterarCorPeao(String corPeao) {
		this.corPeao = corPeao;
	}

	public void alterarNumeroCasaPeaoLiberacao(int numeroCasaPeaoLiberacao) {
		this.numeroCasaPeaoLiberacao = numeroCasaPeaoLiberacao;
	}

	public void alterarPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void alterarVencedor() {
		vencedor = !vencedor;
	}

	public int obterNumeroCasaPeaoLiberacao() {
		return numeroCasaPeaoLiberacao;
	}
	
	public void alterarLancouGravetos() {
		lancouGravetos = !lancouGravetos;
	}
	
	public int obterQtdPeoesLiberados() {
		return qtdPeoesLiberados;
	}
	
	public void alterarQtdPeoesLiberados (int qtdPeoesLiberados) {
		this.qtdPeoesLiberados = qtdPeoesLiberados;
	}

	public void alterarQtdPeoesLiberados () {
		qtdPeoesLiberados += 1;
	}
}