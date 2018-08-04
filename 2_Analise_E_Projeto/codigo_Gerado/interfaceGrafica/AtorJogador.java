package interfaceGrafica;

import servidor.*;
import senet.*;

public class AtorJogador {

	protected senet.Tabuleiro tabuleiro;
	protected AtorServidor servidor;

	public void conectarServidor() {
		// TODO - implement AtorJogador.conectarServidor
		throw new UnsupportedOperationException();
	}

	public boolean procederConectarServidor() {
		// TODO - implement AtorJogador.procederConectarServidor
		throw new UnsupportedOperationException();
	}

	public boolean alterarServidor() {
		// TODO - implement AtorJogador.alterarServidor
		throw new UnsupportedOperationException();
	}

	public Object procederAlterarServidor() {
		// TODO - implement AtorJogador.procederAlterarServidor
		throw new UnsupportedOperationException();
	}

	public Object iniciarNovaPartida() {
		// TODO - implement AtorJogador.iniciarNovaPartida
		throw new UnsupportedOperationException();
	}

	public Object desistirPartida() {
		// TODO - implement AtorJogador.desistirPartida
		throw new UnsupportedOperationException();
	}

	public boolean procederDesistirPartida() {
		// TODO - implement AtorJogador.procederDesistirPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jogada
	 */
	public Object receberJogada(JogadaSenet jogada) {

	}

	/**
	 * 
	 * @param posicao
	 * @param nomeJogador
	 * @param nomeAdversario
	 */
	public Object receberSolicitacaoInicio(int posicao, string nomeJogador, string nomeAdversario) {
		// TODO - implement AtorJogador.receberSolicitacaoInicio
		throw new UnsupportedOperationException();
	}

	public boolean sair() {
		// TODO - implement AtorJogador.sair
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroCasaPeao
	 */
	public Object declararLiberacaoPeao(int numeroCasaPeao) {
		// TODO - implement AtorJogador.declararLiberacaoPeao
		throw new UnsupportedOperationException();
	}

	public void lancarGravetos() {
		// TODO - implement AtorJogador.lancarGravetos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numeroCasaPeao
	 */
	public Object moverPeao(Object numeroCasaPeao) {
		// TODO - implement AtorJogador.moverPeao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param configuracaoTabuleiro
	 */
	public void atualizarExibicaoTabuleiro(ConfiguracaoTabuleiro configuracaoTabuleiro) {

	}

	/**
	 * 
	 * @param mensagem
	 */
	public void receberMensagem(string mensagem) {
		// TODO - implement AtorJogador.receberMensagem
		throw new UnsupportedOperationException();
	}

	public Object obterDadosConexao() {
		// TODO - implement AtorJogador.obterDadosConexao
		throw new UnsupportedOperationException();
	}

}