package senet;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaSenet implements Jogada {
	private static final long serialVersionUID = 1L;
	protected boolean partidaFinalizada;
	protected boolean jogadorVenceu;
	protected int pontuacaoAtualizada;
	protected int casaPeaoMovimentado;
	protected boolean jogadorPassouVez;
	protected String corPeaoEscolhida;
	protected int qtdPeoesAdversarioLiberados;

	public JogadaSenet(boolean partidaFinalizada, boolean jogadorVenceu, int pontuacaoAtualizada,
					   int casaPeaoMovimentado, boolean jogadorPassouVez, String corPeaoEscolhida,
					   int qtdPeoesAdversarioLiberados) {
		this.partidaFinalizada = partidaFinalizada;
		this.jogadorVenceu = jogadorVenceu;
		this.pontuacaoAtualizada = pontuacaoAtualizada;
		this.casaPeaoMovimentado = casaPeaoMovimentado;
		this.jogadorPassouVez = jogadorPassouVez;
		this.corPeaoEscolhida = corPeaoEscolhida;
		this.qtdPeoesAdversarioLiberados = qtdPeoesAdversarioLiberados;
	}
	
	public JogadaSenet() {
		partidaFinalizada = false;
		jogadorVenceu = false;
		pontuacaoAtualizada = 0;
		casaPeaoMovimentado = 0;
		jogadorPassouVez = false;
	}
	
	public boolean obterPartidaFinalizada() {
		return partidaFinalizada;
	}

	public boolean obterJogadorVenceu() {
		return jogadorVenceu;
	}

	public int obterPontuacaoAtualizada() {
		return pontuacaoAtualizada;
	}

	public int obterCasaPeaoMovimentado() {
		return casaPeaoMovimentado;
	}

	public boolean obterJogadorPassouVez() {
		return jogadorPassouVez;
	}

	public void alterarPartidaFinalizada(boolean partidaFinalizada) {
		this.partidaFinalizada = partidaFinalizada;
	}

	public void alterarJogadorVenceu(boolean jogadadorVenceu) {
		this.jogadorVenceu = jogadadorVenceu;
	}

	public void alterarPontuacaoAtualizada(int pontuacaoAtualizada) {
		this.pontuacaoAtualizada = pontuacaoAtualizada;
	}

	public void alterarCasaPeaoMovimentado(int casaPeaoMovimentado) {
		this.casaPeaoMovimentado = casaPeaoMovimentado;
	}

	public void alterarJogadorPassouVez(boolean jogadorPassouVez) {
		jogadorPassouVez = true;
	}
	
	public String obterCorPeaoEscolhida() {
		return corPeaoEscolhida;
	}
	
	public void alterarCorPeaoEscolhida(String corPeaoEscolhida) {
		this.corPeaoEscolhida = corPeaoEscolhida;
	}
	
	
	public int obterQtdPeoesAdversarioLiberados() {
		return qtdPeoesAdversarioLiberados;
	}
	
	public void alterarQtdPeoesAdversarioLiberados(int qtdPeoesAdversarioLiberados) {
		this.qtdPeoesAdversarioLiberados = qtdPeoesAdversarioLiberados;
	}

}