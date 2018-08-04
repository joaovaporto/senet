package DominioDoProblema;

import java.util.Vector;

import javax.swing.JOptionPane;

import DominioDoProblema.Posicao;
import DominioDoProblema.Lance;

public class Tabuleiro {

	protected Posicao posicoes[][] = new Posicao[8][8];
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected boolean conectado;
	protected Vector<Posicao> posicoesAfetadas = new Vector<Posicao>();

	public boolean informarConectado() {
		return conectado;
	}

	public void estabelecerConectado(boolean valor) {
		conectado = valor;
	}

	public boolean informarEmAndamento() {
		return partidaEmAndamento;
	}

	public int click(int linha, int coluna) {
		boolean vez = jogador1.informarDaVez();
		int resultado;
		if (vez) {
			resultado = this.tratarLance(jogador1, linha, coluna);
		}else{
			resultado = 8; 
		}
		return resultado;
	}

	public Lance informarJogada(int linha, int coluna) {
		Lance lance = new Lance();
		lance.assumir(linha, coluna);
		return lance;		
	}

	public ImagemDeTabuleiro informarEstado() {
		ImagemDeTabuleiro estado = new ImagemDeTabuleiro();
		String mensagem;
		boolean vencedor;
		String nomeVencedor = "";	
		int valor = 0;
		int placarBranco = 0;
		int placarPreto = 0;
			// COMPOSICAO DA MENSAGEM
		if (partidaEmAndamento){
			boolean vez = jogador1.informarDaVez();
			if (vez) {
				if (jogador1.informarSimbolo()){
					mensagem = ("Vez do jogador " + jogador1.informarNome() + " (peões brancos)");
				}else{
					mensagem = ("Vez do jogador " + jogador1.informarNome() + " (peões pretos)");
				}
			}else{
				if (jogador2.informarSimbolo()){
					mensagem = ("Vez do jogador " + jogador2.informarNome() + " (peões brancos)");
				}else{
					mensagem = ("Vez do jogador " + jogador2.informarNome() + " (peões pretos)");
				}
			}
		}else{
			vencedor = jogador1.informarVencedor();
			if (vencedor) {
				nomeVencedor = jogador1.informarNome();
			}else{
				vencedor = jogador2.informarVencedor();
				if (vencedor) {
					nomeVencedor = jogador2.informarNome();
				}
			}
			if (vencedor) {
				mensagem = ("VENCEDOR: " + nomeVencedor);
			}else{
				mensagem = ("Partida encerrada com empate");
			}
		}		
		estado.assumirMensagem(mensagem);
			// COMPOSICAO DO MAPA E DO PLACAR
		for (int linha=1; linha<9; linha++){
			for (int coluna=1; coluna<9; coluna++){
				Posicao posicao = this.recuperarPosicao(linha, coluna);
				if (posicao.informarOcupada()) {
					if (posicao.informarOcupante().informarSimbolo()){
						valor = 1;
						placarBranco ++;
					}else{
						valor = 2;
						placarPreto ++;
					}		
				}else{
					valor = 0;
				}
				estado.assumirValor(linha, coluna, valor);
				String textoPlacar = ("(branco) " + (new Integer(placarBranco)).toString() 
							+ " X " + (new Integer(placarPreto)).toString() + " (preto)");
				estado.assumirPlacar("Placar: " + textoPlacar);				
			};	
		};        
		return estado;
	}

	private Posicao recuperarPosicao(int linha, int coluna) {
		return (posicoes[(linha-1)][(coluna-1)]);
	}

	public void iniciar() {
		for (int linha=1; linha<9; linha++){
			for (int coluna=1; coluna<9; coluna++){
				posicoes[(linha-1)][(coluna-1)] = new Posicao();				
			};
		};	
	}

	public void criarJogador(String idJogador) {
		if (jogador1 == null){
			jogador1 = new Jogador();
			jogador1.iniciar();
			jogador1.assumirNome(idJogador);
		}else{
			jogador2 = new Jogador();
			jogador2.iniciar();
			jogador2.assumirNome(idJogador);			
		}
	}

	public void habilitar(Integer posicao) {
		partidaEmAndamento = true;
		if(posicao == 1){
			jogador1.habilitar();
			jogador1.assumirSimbolo(true);
			jogador2.assumirSimbolo(false);
			this.posicionarPeoes(jogador1, jogador2);
		}else{
			jogador2.habilitar();
			jogador2.assumirSimbolo(true);
			jogador1.assumirSimbolo(false);
			this.posicionarPeoes(jogador2, jogador1);
		}
	}

	public void posicionarPeoes(Jogador jogA, Jogador jogB) {
		posicoes[3][3].alocarPeao(jogA);
		posicoes[4][4].alocarPeao(jogA);
		posicoes[4][3].alocarPeao(jogB);
		posicoes[3][4].alocarPeao(jogB);
	}
	
	public void esvaziar() {
		for (int linha=1; linha<9; linha++){
			for (int coluna=1; coluna<9; coluna++){
				posicoes[(linha-1)][(coluna-1)].esvaziar();				
			};
		};	
		jogador1 = null;
		jogador2 = null;
		partidaEmAndamento = false;
		posicoesAfetadas = new Vector<Posicao>();
	}

	public void receberJogada(Lance jogada) {
		int linha = jogada.informarLinha();
		int coluna = jogada.informarColuna();
		boolean vez = jogador1.informarDaVez();
		int resultado;
		if (vez){
			resultado = this.tratarLance(jogador1, linha, coluna);
		}else{
			resultado = this.tratarLance(jogador2, linha, coluna);
		}
		if (resultado == 9){
			this.finalizarPartida();
		}
	}

	public int tratarLance(Jogador jogador, int linha, int coluna) {
		boolean ocupada = this.verificarOcupada(linha, coluna);
		boolean lancePossivel = false;
		boolean vez;
		if (ocupada){
			return 11;
		}else{
			this.zerarPosicoesAfetadas();
			for (int direcao=1; direcao<9; direcao++){
				this.avaliarDirecao(jogador, linha, coluna, direcao);
			}
			if (posicoesAfetadas.size()>0){
				this.reverterPosicoesAfetadas(jogador, linha, coluna);
				vez = jogador1.informarDaVez();
				if (vez){
					jogador1.desabilitar();
					jogador2.habilitar();
					lancePossivel = this.verificarPossibilidadeLance(jogador2);
				}else{
					jogador2.desabilitar();
					jogador1.habilitar();
					lancePossivel = this.verificarPossibilidadeLance(jogador1);
				}
				if (!lancePossivel){
					vez = jogador1.informarDaVez();
					if (vez){
						jogador1.desabilitar();
						jogador2.habilitar();
						lancePossivel = this.verificarPossibilidadeLance(jogador2);
					}else{
						jogador2.desabilitar();
						jogador1.habilitar();
						lancePossivel = this.verificarPossibilidadeLance(jogador1);
					}
				}
				if (lancePossivel){
					return 10;
				}else{
					this.finalizarPartida();
					return 9;
				}	
			}else{
				return 12;
			}
		}		
	}

	public boolean verificarOcupada(int linha, int coluna) {
		Posicao posicao = this.recuperarPosicao(linha, coluna);
		return (posicao.informarOcupada());		
	}

	public void avaliarDirecao(Jogador jogador, int linha, int coluna, int direcao) {
		int vLinha = linha;
		int vColuna = coluna;
		Vector<Posicao> posicoesProvisórias = new Vector<Posicao>();
		Posicao auxPosicao;
		boolean continuar = true;
		boolean fechamento = false;
		while (continuar){
			auxPosicao = this.informarProximaPosicao(vLinha, vColuna, direcao);
			vLinha = this.incrementarLinha(vLinha, direcao);
			vColuna = this.incrementarColuna(vColuna, direcao);
			if (auxPosicao != null){
				if (auxPosicao.informarOcupada()){
					if (auxPosicao.informarOcupante() != jogador){
						posicoesProvisórias.add(auxPosicao);					
					}else{
						continuar = false;
						fechamento = true;
					};
				}else{
					continuar = false;
				};
			}else{
				continuar = false;
			};
		};
		if (posicoesProvisórias.size()>0 && fechamento){
			posicoesAfetadas.addAll(posicoesProvisórias);
		}
	}
	
	public Posicao informarProximaPosicao(int linha, int coluna, int direcao){
		int vLinha = linha;
		int vColuna = coluna;
		switch (direcao) {
			case 1:   vLinha--; break; 					/*Norte*/
			case 2:   {vLinha--; vColuna++;}; break; 	/*Nordeste*/
			case 3:   vColuna++; break; 				/*Leste*/
			case 4:   {vColuna++; vLinha++;}; break; 	/*Sudeste*/
			case 5:   vLinha++; break; 					/*Sul*/
			case 6:   {vColuna--; vLinha++;}; break; 	/*Sudoeste*/
			case 7:   vColuna--; break; 				/*Oeste*/
			case 8:   {vColuna--; vLinha--;}; break; 	/*Noroeste*/
		};
		if ((vLinha>0 && vLinha<9) && (vColuna>0 && vColuna<9)){
			Posicao posRetorno = this.recuperarPosicao(vLinha, vColuna);
			return posRetorno;
		}else{
			return null;
		}
	}
	
	public int incrementarColuna(int coluna, int direcao){
		int vColuna = coluna;
		switch (direcao) {
			case 1:   break; 				/*Norte*/
			case 2:   vColuna++; break; 	/*Nordeste*/
			case 3:   vColuna++; break; 	/*Leste*/
			case 4:   vColuna++; break; 	/*Sudeste*/
			case 5:   break; 				/*Sul*/
			case 6:   vColuna--; break; 	/*Sudoeste*/
			case 7:   vColuna--; break; 	/*Oeste*/
			case 8:   vColuna--; break; 	/*Noroeste*/
		};
		return vColuna;
	}
	
	public int incrementarLinha(int linha, int direcao){
		int vLinha = linha;
		switch (direcao) {
			case 1:   vLinha--; break; 		/*Norte*/
			case 2:   vLinha--; break; 		/*Nordeste*/
			case 3:   break; 				/*Leste*/
			case 4:   vLinha++; break; 		/*Sudeste*/
			case 5:   vLinha++; break; 		/*Sul*/
			case 6:   vLinha++; break; 		/*Sudoeste*/
			case 7:   break; 				/*Oeste*/
			case 8:   vLinha--; break; 		/*Noroeste*/
		};
		return vLinha;
	}	

	public void reverterPosicoesAfetadas(Jogador jogador, int linha, int coluna) {
		Posicao posicao = this.recuperarPosicao(linha, coluna);
		Posicao auxPosicao;
		posicoesAfetadas.add(posicao);
		for (int indice=0; indice<(posicoesAfetadas.size()); indice++){
			auxPosicao = posicoesAfetadas.get(indice);
			auxPosicao.alocarPeao(jogador);
		};
	}

	public void zerarPosicoesAfetadas() {
		posicoesAfetadas = new Vector<Posicao>();
	}

	public boolean verificarPossibilidadeLance(Jogador jogador) {
		Posicao auxPosicao;
		for (int linha=1; linha<9; linha++){
			for (int coluna=1; coluna<9; coluna++){
				auxPosicao = this.recuperarPosicao(linha, coluna);
				if (!(auxPosicao.informarOcupada())){
					this.zerarPosicoesAfetadas();
					for (int direcao=1; direcao<9; direcao++){
						this.avaliarDirecao(jogador, linha, coluna, direcao);
					}
					if (posicoesAfetadas.size()>0){
						return true;
					}	
				}
			}
		};
		return false;
	}

	public void finalizarPartida() {
		int placar1 = this.informarPlacar(jogador1);
		int placar2 = this.informarPlacar(jogador2);
		partidaEmAndamento = false;
		jogador1.desabilitar();
		jogador2.desabilitar();
		if (placar1 > placar2){
			jogador1.assumirVencedor();
		}else{
			if (placar2 > placar1){
				jogador2.assumirVencedor();
			}
		}
	}
	
	public int informarPlacar(Jogador jogador){
		Posicao auxPosicao;
		int contador=0;
		for (int linha=1; linha<9; linha++){
			for (int coluna=1; coluna<9; coluna++){
				auxPosicao = this.recuperarPosicao(linha, coluna);
				if ((auxPosicao.informarOcupada())){
					if (auxPosicao.informarOcupante() == jogador){
						contador++;
					}
				}
			}
		}
		return contador;		
	}

}