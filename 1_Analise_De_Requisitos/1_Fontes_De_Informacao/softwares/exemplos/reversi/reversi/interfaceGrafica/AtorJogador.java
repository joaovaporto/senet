package interfaceGrafica;

import br.ufsc.inf.leobr.cliente.Proxy;
import DominioDoProblema.ImagemDeTabuleiro;
import DominioDoProblema.Lance;
import DominioDoProblema.Tabuleiro;
import Rede.AtorNetGames;

public class AtorJogador {

	protected Tabuleiro tab;
	protected AtorNetGames rede;
	protected InterfaceReversi janela;
	protected String idUsuario;

	public AtorJogador (InterfaceReversi jan){
		super();
		rede = new AtorNetGames(this);
		janela = jan;
		tab = new Tabuleiro();
		tab.iniciar();
	}

	public int conectar() {
		boolean conectado = tab.informarConectado();
		if (!conectado){
			String servidor = this.obterDadosConexao();
			boolean exito = rede.conectar(servidor, idUsuario);
			if (exito){
				tab.estabelecerConectado(true);
				return 0;
			}else{
				return 2;
			}			
		}else{
			return 1;
		}		
	}

	public InterfaceReversi informarJanela() {
		return janela;
	}

	public String obterDadosConexao() {
		idUsuario = janela.obterIdJogador();
		String servidor = janela.obterIdServidor();
		return servidor;
	}

//	public void notificarIrregularidade(int cod) {
//		throw new UnsupportedOperationException();
//	}

	public int desconectar() {
		boolean conectado = tab.informarConectado();
		if (conectado){
			boolean exito = rede.desconectar();
			if (exito){
				tab.estabelecerConectado(false);
				return 3;
			}else{
				return 5;
			}			
		}else{
			return 4;
		}			
	}

	public int iniciarPartida() {
		boolean conectado = false;
		boolean interromper = false;
		boolean emAndamento = tab.informarEmAndamento();
		if (emAndamento){
			interromper = this.avaliarInterrupcao();
		}else{
			conectado = tab.informarConectado();
		}
		if (interromper || ((!emAndamento) && conectado)){
			rede.iniciarPartida();
			return 6;
		}
		if (!conectado) {
			return 7;
		}
		return 13;
	}

	public boolean avaliarInterrupcao() {
		return true;
	}

	public int click(int linha, int coluna) {
		int resultado = 0;
		resultado = tab.click(linha, coluna);
		if ((resultado == 10) || (resultado == 9)){
			this.enviarJogada(linha, coluna);		
		}
		return resultado;
	}

	public void enviarJogada(int linha, int coluna) {
		Lance lance = tab.informarJogada(linha, coluna);
		rede.enviarJogada(lance);
	}

//	public void exibirEstado() {
//		throw new UnsupportedOperationException();
//	}

	public ImagemDeTabuleiro informarEstado() {
		ImagemDeTabuleiro estado = tab.informarEstado();
		return estado;
	}
	
	public void tratarIniciarPartida(Integer posicao) {
		tab.esvaziar();
		tab.criarJogador(idUsuario);
		String idJogador = rede.informarNomeAdversario(idUsuario);
		tab.criarJogador(idJogador);
		tab.habilitar(posicao);
		janela.exibirEstado();		
	}

	public void receberJogada(Lance jogada) {
		tab.receberJogada(jogada);
		janela.exibirEstado();
	}

}