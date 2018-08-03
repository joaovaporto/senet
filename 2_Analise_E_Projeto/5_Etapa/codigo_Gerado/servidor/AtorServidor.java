package servidor;

import interfaceGrafica.*;
import senet.*;

public class AtorServidor extends OuvidorProxy {

	protected AtorJogador interfaceGrafica;
	protected Proxy proxy;
	protected boolean conectado;
	protected string nomeJogador;
	protected string nomeServidor;

	/**
	 * 
	 * @param servidor
	 * @param nome
	 */
	public boolean conectar(string servidor, string nome) {
		// TODO - implement AtorServidor.conectar
		throw new UnsupportedOperationException();
	}

	public void desconectar() {
		// TODO - implement AtorServidor.desconectar
		throw new UnsupportedOperationException();
	}

	public Object iniciarPartida() {
		// TODO - implement AtorServidor.iniciarPartida
		throw new UnsupportedOperationException();
	}

	public Object receberJogada() {
		// TODO - implement AtorServidor.receberJogada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomeJogador
	 * @param nomeServidor
	 */
	public Object salvarDadosConectarServidor(string nomeJogador, string nomeServidor) {
		// TODO - implement AtorServidor.salvarDadosConectarServidor
		throw new UnsupportedOperationException();
	}

	public boolean restabelecerConexao() {
		// TODO - implement AtorServidor.restabelecerConexao
		throw new UnsupportedOperationException();
	}

	public void atualizarDadosDeConexao() {
		// TODO - implement AtorServidor.atualizarDadosDeConexao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jogada
	 */
	public void enviarJogada(JogadaSenet jogada) {

	}

	public void procederAlterarServidor() {
		// TODO - implement AtorServidor.procederAlterarServidor
		throw new UnsupportedOperationException();
	}

	public boolean procederConectarServidor() {
		// TODO - implement AtorServidor.procederConectarServidor
		throw new UnsupportedOperationException();
	}

	public Object procederDesconectarServidor() {
		// TODO - implement AtorServidor.procederDesconectarServidor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param posicao
	 */
	public Object iniciarNovaPartida(int posicao) {
		// TODO - implement AtorServidor.iniciarNovaPartida
		throw new UnsupportedOperationException();
	}

	public Object procederIniciarNovaPartida() {
		// TODO - implement AtorServidor.procederIniciarNovaPartida
		throw new UnsupportedOperationException();
	}

	public boolean avaliarServidorConectado() {
		// TODO - implement AtorServidor.avaliarServidorConectado
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomeJogador
	 * @param nomeServidor
	 */
	public boolean avaliarDadosConectarValidos(Object nomeJogador, Object nomeServidor) {
		// TODO - implement AtorServidor.avaliarDadosConectarValidos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomeJogador
	 */
	public boolean avaliarNomeJogadorValido(string nomeJogador) {
		// TODO - implement AtorServidor.avaliarNomeJogadorValido
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomeServidor
	 */
	public Object avaliarNomeServidorValido(Object nomeServidor) {
		// TODO - implement AtorServidor.avaliarNomeServidorValido
		throw new UnsupportedOperationException();
	}

	public string obterNomeJogador() {
		// TODO - implement AtorServidor.obterNomeJogador
		throw new UnsupportedOperationException();
	}

	public string obterNomeServidor() {
		// TODO - implement AtorServidor.obterNomeServidor
		throw new UnsupportedOperationException();
	}

	public void alterarNomeJogador() {
		// TODO - implement AtorServidor.alterarNomeJogador
		throw new UnsupportedOperationException();
	}

	public void alterarNomeServidor() {
		// TODO - implement AtorServidor.alterarNomeServidor
		throw new UnsupportedOperationException();
	}

}