package senet;

import java.util.ArrayList;
import java.util.Random;
import interfaceGrafica.AtorJogador;
import interfaceGrafica.Constante;

public class Tabuleiro {
	protected AtorJogador atorJogador;
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected boolean partidaEmAndamento;
	protected boolean liberacaoEmAndamento;
	protected Casa[] casas;
	protected Graveto[] gravetos;
	protected int pontuacaoAtual;
	protected ConfiguracaoTabuleiro configuracaoTabuleiro;

	public Tabuleiro(AtorJogador atorJogador) {
		this.atorJogador = atorJogador;
		partidaEmAndamento = false;
		liberacaoEmAndamento = false;
		inicializarGravetos();
		configuracaoTabuleiro = new ConfiguracaoTabuleiro();
	}
	
	public void inicializarGravetos() {
		gravetos = new Graveto[4];
		
		for (int i = 0; i < 4; i++)
			gravetos[i] = new Graveto();
	}

	public JogadaSenet lancarGravetos() {
		if (!ehTurnoDoJogador())
			return null;
		
		JogadaSenet jogada;
		int numeroCasaPeaoMovimentado = 0;

		alterarPontuacaoAtual(jogador1.sortearPontuacao());
		atorJogador.alterarConfiguracaoTabuleiro();
		
		if (!avaliarCorPeaoJogadorDefinida())
			definirCorPeoes();
		else {
			if (!obterLiberacaoEmAndamento()) {
				if (!avaliarPossivelMovimentar(obterPontuacaoAtual())) {
					atorJogador.receberMensagem(Constante.SEM_MOVIMENTO_POSSIVEL);
					mudarVezJogadores();
				} else {
					jogador1.alterarLancouGravetos();
					return null;
				}	
			}
				
			if (obterLiberacaoEmAndamento()) {
				numeroCasaPeaoMovimentado = jogador1.obterNumeroCasaPeaoLiberacao();
				diligenciarLiberacaoPeao();
			}
		}
		
		jogada = new JogadaSenet(!obterPartidaEmAndamento(),
			     				 jogador1.obterVencedor(),
			     				 obterPontuacaoAtual(),
			     				 numeroCasaPeaoMovimentado,
			     				 !jogador1.obterJogadorDaVez(),
			     				 jogador1.obterCorPeao(),
			     				 jogador2.obterQtdPeoesLiberados());
		
		if (jogador1.obterVencedor())
			finalizarPartida();
			
		return jogada;
	}
	
	public void diligenciarLiberacaoPeao() {
		if (avaliarPontuacaoSuficiente(jogador1.obterNumeroCasaPeaoLiberacao(), obterPontuacaoAtual())) {
			liberarPeao(jogador1.obterNumeroCasaPeaoLiberacao());
			
			if (!avaliarRestamPeoesJogador()) {
				alterarPartidaEmAndamento();
				jogador1.alterarVencedor();
				atorJogador.alterarConfiguracaoTabuleiro();
				atorJogador.receberMensagem(Constante.JOGADOR_VENCEU);
			} else
				mudarVezJogadores();
		} else {
			moverPeaoTabuleiro(jogador1.obterNumeroCasaPeaoLiberacao(), 27 - jogador1.obterNumeroCasaPeaoLiberacao());
			mudarVezJogadores();
		}
		
		jogador1.alterarNumeroCasaPeaoLiberacao(0);
		alterarLiberacaoEmAndamento();
	}

	public void definirCorPeoes() {
		if (jogador2.obterPontuacao() != 0) {
			while (jogador1.obterPontuacao() == jogador2.obterPontuacao())
				alterarPontuacaoAtual(jogador1.sortearPontuacao());
			
			if (jogador1.obterPontuacao() > jogador2.obterPontuacao()) {
				atorJogador.alterarConfiguracaoTabuleiro();
				jogador1.alterarCorPeao(atorJogador.obterCorPeaoEscolhida());
				jogador2.alterarCorPeao(obterCorOposta(jogador1.obterCorPeao()));
				configuracaoTabuleiro.alterarCorPeaoJogador(jogador1.obterCorPeao());
				configuracaoTabuleiro.alterarCorPeaoAdversario(jogador2.obterCorPeao());
				
				return;
			} else
				atorJogador.receberMensagem("Seu adversário inicia e escolhe a cor do peão.");
		}
		
		mudarVezJogadores();
	}
	
	public void moverPeaoParaTras(int numeroCasaPeao) {
		int i;
		
		for (i = 15; ; i--)
			if (!obterCasa(i).obterOcupada())
				break;
		
		mudarCasaPeao(numeroCasaPeao, i - numeroCasaPeao);
	}
	
	public JogadaSenet moverPeao(int numeroCasaPeao) {
		if (!ehTurnoDoJogador())
			return null;
		
		if (!avaliarCorPeaoJogadorDefinida())
			return null;
		
		if (!jogador1.obterLancouGravetos()) {
			atorJogador.receberMensagem(Constante.SEM_GRAVETOS_LANCADOS);
			return null;
		}
		
		if (!ehCasaOcupadaPorPeaoJogador(numeroCasaPeao)) {
			atorJogador.receberMensagem(Constante.SEM_PEAO_NA_CASA);
			return null;
		}
		
		int deslocamento = obterPontuacaoAtual();
		
		if (deslocamento > 30)
			deslocamento = 30;
		
		if (!avaliarMovimentoValido(numeroCasaPeao, deslocamento)) {
			atorJogador.receberMensagem(Constante.MOVIMENTO_INVALIDO);
			return null;
		}
		
		moverPeaoTabuleiro(numeroCasaPeao, obterPontuacaoAtual());
		jogador1.alterarLancouGravetos();
		
		if (!avaliarJogarNovamente(obterPontuacaoAtual()))
			mudarVezJogadores();
		
		return new JogadaSenet(!obterPartidaEmAndamento(),
				   jogador1.obterVencedor(),
				   obterPontuacaoAtual(),
				   numeroCasaPeao,
				   !jogador1.obterJogadorDaVez(),
				   jogador1.obterCorPeao(),
				   jogador2.obterQtdPeoesLiberados());
	}

	public boolean declararLiberacaoPeao(int numeroCasaPeao) {
		if (!ehTurnoDoJogador())
			return false;
		
		if (!avaliarCorPeaoJogadorDefinida()) {
			atorJogador.receberMensagem(Constante.SEM_COR_PEAO_DEFINIDA);
			return false;
		}
		
		if (!ehCasaOcupadaPorPeaoJogador(numeroCasaPeao)) {
			atorJogador.receberMensagem(Constante.SEM_PEAO_LIBERACAO);
			return false;
		}
		
		jogador1.alterarNumeroCasaPeaoLiberacao(numeroCasaPeao);
		alterarLiberacaoEmAndamento();
		atorJogador.receberMensagem(Constante.LIBERACAO_DECLARADA);
		return true;
	}
	
	public boolean ehCasaOcupadaPorPeaoJogador(int numeroCasaPeao) {
		Casa casa = obterCasa(numeroCasaPeao);
		
		if (!casa.obterOcupada())
			return false;
		
		return jogador1.ehSuaCorPeao(casa.obterCorPeaoOcupante());
	}

	public boolean avaliarCorPeaoJogadorDefinida() {
		if (!jogador1.avaliarPeoesDefinidos()) {
			atorJogador.receberMensagem(Constante.SEM_COR_PEAO_DEFINIDA);
			return false;
		}
		
		return true;
	}

	public void iniciarNovaPartida(int posicao, String nomeJogador, String nomeAdversario) {
		alterarPartidaEmAndamento();
		inicializarCasas();
		inicializarJogadores(nomeJogador, nomeAdversario);
		
		if (posicao == 1)
			jogador1.alterarJogadorDaVez();
		else
			jogador2.alterarJogadorDaVez();
		
		configuracaoTabuleiro.alterarNomeJogadorDaVez(obterJogadorDaVez().obterNomeJogador());
	}

	public void inicializarJogadores(String nomeJogador, String nomeAdversario) {
		jogador1 = new Jogador(nomeJogador);
		jogador2 = new Jogador(nomeAdversario);
	}

	public void inicializarCasas() {
		casas = new Casa[30];
		
		for (int i = 0; i < 30; i++)
			casas[i] = new Casa(i+1);
		
		for (int i = 0; i < 5; i++) {
			int j = i + 26;
			
			if (j == 27) 
				continue;
			
			obterCasa(j).alterarSegura();
		}
		
		for (int i = 0; i < 14; i++) {
			obterCasa(i+1).alterarPeao(new Peao(Constante.PEAO_CLARO));
			obterCasa(i+1).alterarOcupada();
			
			if (i % 2 == 0)
				obterCasa(i+1).alterarPeao(new Peao(Constante.PEAO_ESCURO));
		}
		
		configuracaoTabuleiro.alterarConteudoCasas(casas);
	}

	public JogadaSenet desistirPartida() {
		JogadaSenet jogada = new JogadaSenet(true,
											 jogador1.obterVencedor(),
											 obterPontuacaoAtual(),
											 0,
											 !jogador1.obterJogadorDaVez(),
											 jogador1.obterCorPeao(),
											 jogador2.obterQtdPeoesLiberados());
		
		finalizarPartida();
		return jogada;		
	}

	public void finalizarPartida() {
		alterarPartidaEmAndamento();
		casas = null;
		alterarPontuacaoAtual(0);
		jogador1 = null;
		jogador2 = null;
		configuracaoTabuleiro.zerarConfiguracao();
	}

	public void moverPeaoTabuleiro(int numeroCasaPeao, int deslocamento) {
		Casa casaOrigem = obterCasa(numeroCasaPeao);
		Casa casaDestino = obterCasa(30);
		
		if (numeroCasaPeao + deslocamento < 30)
			casaDestino = obterCasa(numeroCasaPeao + deslocamento);
		
		if (!casaDestino.obterOcupada()) {
			if (casaDestino.obterNumero() == 27)
				moverPeaoParaTras(numeroCasaPeao);
			
			if (casaDestino.obterNumero() > 27) {
				if (casaOrigem.obterNumero() == 26) {
					if (casaDestino.obterNumero() == 30)
						liberarPeao(casaOrigem.obterNumero());
					else
						mudarCasaPeao(numeroCasaPeao, deslocamento);
				} else
					mudarCasaPeao(numeroCasaPeao, 26 - numeroCasaPeao);
			}
			
			if (casaDestino.obterNumero() < 27)
				mudarCasaPeao(numeroCasaPeao, deslocamento);
		} else if (casaDestino.obterNumero() > 27) {
			mudarCasaPeao(numeroCasaPeao, 26 - numeroCasaPeao);
		} else			
			trocarPeoesDeCasa(numeroCasaPeao, deslocamento);
		
		configuracaoTabuleiro.alterarConteudoCasas(casas);
	}

	public void trocarPeoesDeCasa(int numeroCasaPeao, int deslocamento) {
		Casa casaOrigem = obterCasa(numeroCasaPeao);
		Casa casaDestino = obterCasa(numeroCasaPeao + deslocamento);
		Peao peaoAdversario = casaDestino.obterPeao();
		
		casaDestino.alterarPeao(casaOrigem.obterPeao());
		casaOrigem.alterarPeao(peaoAdversario);		
	}

	public void mudarCasaPeao(int numeroCasaPeao, int deslocamento) {
		Casa casaOrigem = obterCasa(numeroCasaPeao);
		Casa casaDestino = obterCasa(numeroCasaPeao + deslocamento);
		
		casaDestino.alterarOcupada();
		casaDestino.alterarPeao(casaOrigem.obterPeao());
		casaOrigem.alterarOcupada();
		casaOrigem.alterarPeao(null);		
	}

	public void mudarVezJogadores() {
		jogador1.alterarJogadorDaVez();
		jogador2.alterarJogadorDaVez();
		configuracaoTabuleiro.alterarNomeJogadorDaVez(obterJogadorDaVez().obterNomeJogador());
	}

	public void liberarPeao(int numeroCasa) {	
		Casa casaPeao = obterCasa(numeroCasa);
		
		if (jogador1.ehSuaCorPeao(casaPeao.obterCorPeaoOcupante())) {
			jogador1.alterarQtdPeoesLiberados();
			configuracaoTabuleiro.alterarQtdPeoesJogadorLiberados(jogador1.obterQtdPeoesLiberados());
		} else {
			jogador2.alterarQtdPeoesLiberados();
			configuracaoTabuleiro.alterarQtdPeoesAdversarioLiberados(jogador2.obterQtdPeoesLiberados());
		}

		casaPeao.alterarPeao(null);
		casaPeao.alterarOcupada();
		configuracaoTabuleiro.alterarConteudoCasas(casas);
	}

	public boolean avaliarMovimentoValido(int numeroCasaPeao, int deslocamento) {
		Casa casaDestino = obterCasa(30);
		
		if (numeroCasaPeao + deslocamento < 30)
			casaDestino = obterCasa(numeroCasaPeao + deslocamento);
		
		if (numeroCasaPeao == 28 || numeroCasaPeao == 29 || numeroCasaPeao == 30)
			return false;
		
		if (casaDestino.obterOcupada())
			if (numeroCasaPeao != 26)
				if (ehCasaOcupadaPorPeaoJogador(casaDestino.obterNumero()))
					return false;
		
		if (casaDestino.obterOcupada())
				if (casaDestino.obterSegura())
					return false;
	
		if (ehCasaDestinoBloqueada(numeroCasaPeao, deslocamento))
				return false;
		
		if (casaDestino.obterNumero() > 27)
			if (numeroCasaPeao != 26)
				if (obterCasa(26).obterOcupada())
					return false;
		
		return true;
	}

	public boolean ehCasaDestinoBloqueada(int numeroCasa, int deslocamento) {
		int numCasaDest = numeroCasa + deslocamento;
		
		if (numCasaDest > 30)
			numCasaDest = 30;
		
		String corPeaoAdversario = jogador2.obterCorPeao();
		
		if (obterCasa(numCasaDest).obterOcupada()) {
			if (obterCasa(numCasaDest - 1).obterOcupada())
				if (corPeaoAdversario.equals(obterCasa(numCasaDest - 1).obterCorPeaoOcupante()))
					return true;
			
			if (obterCasa(numCasaDest + 1).obterOcupada())
				if (corPeaoAdversario.equals(obterCasa(numCasaDest + 1).obterCorPeaoOcupante()))
					return true;
			
			return false;
		}
		
		if (deslocamento < 4)
			return false;
		
		int k = 0;
		
		for (int i = 0; i < deslocamento - 1; i++) {
			int j = numeroCasa + i + 1;
			
			if (obterCasa(j).obterOcupada())
				if (corPeaoAdversario.equals(obterCasa(j).obterCorPeaoOcupante())) {
					k++;
					
					if (k == 3)
						return true;
					
					continue;
				}
			
			k = 0;
		}
		
		return false;		
	}

	public boolean ehTurnoDoJogador() {
		if (!obterPartidaEmAndamento())
			atorJogador.receberMensagem(Constante.SEM_PARTIDA);
		else if (!jogador1.obterJogadorDaVez())
			atorJogador.receberMensagem(Constante.SEM_VEZ_JOGADOR);
		else 
			return true;
		
		return false;
	}

	public boolean avaliarPontuacaoSuficiente(int numeroCasaLiberacao, int pontuacao) {
		if (numeroCasaLiberacao == 28)
			if (pontuacao >= 3)
				return true;
		
		if (numeroCasaLiberacao == 29)
			if (pontuacao >= 2)
				return true;
		
		return false;
	}

	public boolean avaliarJogarNovamente(int pontuacao) {
		if (pontuacao == 2 || pontuacao == 4)
			return true;
		
		return false;
	}

	public boolean avaliarPossivelMovimentar(int pontuacao) {
		for (int i = 0; i < 30; i++) {
			if (!ehCasaOcupadaPorPeaoJogador(i+1))
				continue;
			
			if (avaliarMovimentoValido(i+1, pontuacao))
				return true;
		}
		
		return false;
	}

	public boolean avaliarRestamPeoesJogador() {
		for (int i = 0; i < 30; i++) {
			if (ehCasaOcupadaPorPeaoJogador(i+1))
				return true;
		}
		
		return false;
	}

	public boolean obterLiberacaoEmAndamento() {
		return liberacaoEmAndamento;
	}

	public Jogador obterJogadorDaVez() {
		if (jogador1.obterJogadorDaVez())
			return jogador1;
			
		return jogador2;
	}
	
	public boolean obterPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public Casa obterCasa(int numeroCasa) {
		return casas[numeroCasa - 1];
	}

	public int obterPontuacaoAtual() {
		return pontuacaoAtual;
	}

	public ConfiguracaoTabuleiro obterConfiguracaoTabuleiro() {
		return configuracaoTabuleiro;
	}

	public void alterarPartidaEmAndamento() {
		partidaEmAndamento = !partidaEmAndamento;
	}

	public void alterarLiberacaoEmAndamento() {
		liberacaoEmAndamento = !liberacaoEmAndamento;
		configuracaoTabuleiro.alterarNumeroCasaPeaoLiberacao(jogador1.obterNumeroCasaPeaoLiberacao());
	}

	public void alterarPontuacaoAtual(int pontuacaoAtual) {
		this.pontuacaoAtual = pontuacaoAtual;
		alterarGravetos();
		configuracaoTabuleiro.alterarPontuacao(pontuacaoAtual);
	}

	public void alterarGravetos() {
		int pontuacao = obterPontuacaoAtual();
		
		for (int i = 0; i < 4; i++)
			gravetos[i].alterarRiscado(false);
		
		if (pontuacao == 0 || pontuacao == 5) {
			configuracaoTabuleiro.alterarGravetosRiscados(gravetos);
			return;
		}
		
		Random gerador = new Random();
		ArrayList<Integer> usados = new ArrayList<Integer>(5);
		
		for (int i = 0; i < pontuacao; i++) {
			int j;
			while (usados.contains(j = gerador.nextInt(4)));
			usados.add(j);
			gravetos[j].alterarRiscado(true);
		}
		
		configuracaoTabuleiro.alterarGravetosRiscados(gravetos);
	}

	public String obterCorOposta(String cor) {
		if (cor == null)
			return null;
		
		if (cor.equals(Constante.PEAO_CLARO))
			return Constante.PEAO_ESCURO;
		else
			return Constante.PEAO_CLARO;
	}
	
	public void receberJogada(JogadaSenet jogada) {
		if (jogada.obterJogadorPassouVez())
			mudarVezJogadores();
		
		alterarPontuacaoAtual(jogada.obterPontuacaoAtualizada());
		jogador2.alterarPontuacao(jogada.obterPontuacaoAtualizada());
		
		atorJogador.alterarConfiguracaoTabuleiro();
		
		if (!avaliarCorPeaoJogadorDefinida()) {
			if (jogada.obterCorPeaoEscolhida() != null) {
				jogador1.alterarCorPeao(atorJogador.obterCorPeaoEscolhida());
				jogador2.alterarCorPeao(obterCorOposta(jogador1.obterCorPeao()));
				configuracaoTabuleiro.alterarCorPeaoJogador(jogador1.obterCorPeao());
				configuracaoTabuleiro.alterarCorPeaoAdversario(jogador2.obterCorPeao());
				jogador1.alterarCorPeao(obterCorOposta(jogada.obterCorPeaoEscolhida()));
				jogador2.alterarCorPeao(jogada.obterCorPeaoEscolhida());
				configuracaoTabuleiro.alterarCorPeaoJogador(jogador1.obterCorPeao());
				configuracaoTabuleiro.alterarCorPeaoAdversario(jogador2.obterCorPeao());
				atorJogador.receberMensagem("Seu peão é " + jogador1.obterCorPeao() + ".");
			} else if (jogador1.obterPontuacao() != 0) {
				definirCorPeoes();
			} else {
				atorJogador.receberMensagem("Sua vez de lançar os gravetos para definir quem começa.");
			}	
		}		
		
		int numeroCasaPeaoMovimentado = jogada.obterCasaPeaoMovimentado();
		
		if (numeroCasaPeaoMovimentado != 0) {
			if (numeroCasaPeaoMovimentado == 28 || numeroCasaPeaoMovimentado == 29) {
				if (avaliarPontuacaoSuficiente(numeroCasaPeaoMovimentado, jogada.obterPontuacaoAtualizada()))
					liberarPeao(numeroCasaPeaoMovimentado);
				else
					moverPeaoTabuleiro(numeroCasaPeaoMovimentado, 27 - numeroCasaPeaoMovimentado);
			} else
				moverPeaoTabuleiro(numeroCasaPeaoMovimentado, jogada.pontuacaoAtualizada);
		}
		
		if (jogada.obterPartidaFinalizada()) {
			atorJogador.alterarConfiguracaoTabuleiro();
			
			if (jogada.obterJogadorVenceu())
				atorJogador.receberMensagem(Constante.JOGADOR_PERDEU);
			else
				atorJogador.receberMensagem(Constante.ADVERSARIO_DESISTIU);
			
			finalizarPartida();
		}
	}
}