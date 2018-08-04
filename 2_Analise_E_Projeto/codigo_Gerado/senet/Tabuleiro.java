package senet;

public class Tabuleiro {

	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected boolean liberacaoEmAndamento;
	protected Casa casas;
	protected int pontuacaoAtual;

	public JogadaSenet lancarGravetos() {

	}

	/**
	 * 
	 * @param numeroCasaPeao
	 */
	public JogadaSenet moverPeao(Object numeroCasaPeao) {

	}

	/**
	 * 
	 * @param numeroCasaPeao
	 */
	public void declararLiberacaoPeao(int numeroCasaPeao) {
		// TODO - implement Tabuleiro.declararLiberacaoPeao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param posicao
	 * @param nomeJogador
	 * @param nomeAdversario
	 */
	public boolean iniciarNovaPartida(int posicao, string nomeJogador, string nomeAdversario) {
		// TODO - implement Tabuleiro.iniciarNovaPartida
		throw new UnsupportedOperationException();
	}

	public JogadaSenet desistirPartida() {

	}

	public void finalizarPartida() {
		// TODO - implement Tabuleiro.finalizarPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jogada
	 */
	public void atualizarTabuleiro(JogadaSenet jogada) {

	}

	/**
	 * 
	 * @param jogada
	 */
	public string atualizarPartida(JogadaSenet jogada) {

	}

	/**
	 * 
	 * @param numeroCasaPeao
	 * @param deslocamento
	 */
	public Object moverPeaoTabuleiro(int numeroCasaPeao, int deslocamento) {
		// TODO - implement Tabuleiro.moverPeaoTabuleiro
		throw new UnsupportedOperationException();
	}

	public void mudarVez() {
		// TODO - implement Tabuleiro.mudarVez
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param corPeaoJogador
	 */
	public Object definirCorPeaoJogadores(Object corPeaoJogador) {
		// TODO - implement Tabuleiro.definirCorPeaoJogadores
		throw new UnsupportedOperationException();
	}

	public void eliminarCasas() {
		// TODO - implement Tabuleiro.eliminarCasas
		throw new UnsupportedOperationException();
	}

	public Object eliminarJogadores() {
		// TODO - implement Tabuleiro.eliminarJogadores
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroCasaPeao
	 */
	public void liberarPeao(int numeroCasaPeao) {
		// TODO - implement Tabuleiro.liberarPeao
		throw new UnsupportedOperationException();
	}

	public boolean avaliarPartidaEmAndamento() {
		// TODO - implement Tabuleiro.avaliarPartidaEmAndamento
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroCasaPeao
	 * @param deslocamento
	 */
	public boolean avaliarMovimentoValido(int numeroCasaPeao, Object deslocamento) {
		// TODO - implement Tabuleiro.avaliarMovimentoValido
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tipoJogada
	 */
	public string avaliarJogadaPermitida(string tipoJogada) {
		// TODO - implement Tabuleiro.avaliarJogadaPermitida
		throw new UnsupportedOperationException();
	}

	public boolean avaliarPontuacaoSuficiente() {
		// TODO - implement Tabuleiro.avaliarPontuacaoSuficiente
		throw new UnsupportedOperationException();
	}

	public boolean avaliarJogarNovamente() {
		// TODO - implement Tabuleiro.avaliarJogarNovamente
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pontuacao
	 */
	public Object avaliarPossivelMovimentar(int pontuacao) {
		// TODO - implement Tabuleiro.avaliarPossivelMovimentar
		throw new UnsupportedOperationException();
	}

	public boolean avaliarRestamPeoesJogador() {
		// TODO - implement Tabuleiro.avaliarRestamPeoesJogador
		throw new UnsupportedOperationException();
	}

	public boolean avaliarPontuacaoJogadoresIguais() {
		// TODO - implement Tabuleiro.avaliarPontuacaoJogadoresIguais
		throw new UnsupportedOperationException();
	}

	public boolean avaliarAdversarioPossuiMaiorPontuacao() {
		// TODO - implement Tabuleiro.avaliarAdversarioPossuiMaiorPontuacao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroCasaPeao
	 */
	public Object avaliarCasasAdjacentesOcupadas(Object numeroCasaPeao) {
		// TODO - implement Tabuleiro.avaliarCasasAdjacentesOcupadas
		throw new UnsupportedOperationException();
	}

	public boolean obterLiberacaoEmAndamento() {
		// TODO - implement Tabuleiro.obterLiberacaoEmAndamento
		throw new UnsupportedOperationException();
	}

	public boolean obterPartidaEmAndamento() {
		// TODO - implement Tabuleiro.obterPartidaEmAndamento
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroCasa
	 */
	public Casa obterCasa(Object numeroCasa) {

	}

	public int obterPontuacaoAtual() {
		// TODO - implement Tabuleiro.obterPontuacaoAtual
		throw new UnsupportedOperationException();
	}

	public ConfiguracaoTabuleiro obterConfiguracaoTabuleiro() {

	}

	/**
	 * 
	 * @param partidaEmAndamento
	 */
	public Object alterarPartidaEmAndamento(boolean partidaEmAndamento) {
		// TODO - implement Tabuleiro.alterarPartidaEmAndamento
		throw new UnsupportedOperationException();
	}

	public boolean alterarLiberacaoEmAndamento() {
		// TODO - implement Tabuleiro.alterarLiberacaoEmAndamento
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pontuacao
	 */
	public void alterarPontuacaoAtual(int pontuacao) {
		// TODO - implement Tabuleiro.alterarPontuacaoAtual
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomeJogador
	 */
	public void alterarNomeJogadorDaVez(string nomeJogador) {
		// TODO - implement Tabuleiro.alterarNomeJogadorDaVez
		throw new UnsupportedOperationException();
	}

}