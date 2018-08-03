package interfaceGrafica;

import java.awt.*;
import javax.swing.*;

import senet.ConfiguracaoTabuleiro;

public class ConteudoJanela extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected Transcendente transcendenteAdversario;
	protected Tabuleiro tabuleiro;
	protected Graveteiro graveteiro;
	protected Transcendente transcendenteJogador;
	protected Botaozeiro botaozeiro;
	protected Status status;
	
	ConteudoJanela(TratadoraDeEvento tratadoraDeEvento) {
		configurarConteudo();
		carregarComponentes(tratadoraDeEvento);
	}
	
	public void configurarConteudo() {
		setPreferredSize(new Dimension(1150,705));
		setLayout(null);
		setBackground(Color.WHITE);
	}
	
	public void carregarComponentes(TratadoraDeEvento tratadoraDeEvento) {
		transcendenteAdversario = new Transcendente();
		transcendenteAdversario.setBounds(375, 20, 700, 100);
		add(transcendenteAdversario);
		
		tabuleiro = new Tabuleiro(tratadoraDeEvento);
		tabuleiro.setBounds(75, 140, 1000, 300);
		add(tabuleiro);
		
		graveteiro = new Graveteiro();
		graveteiro.setBounds(125, 450, 135, 150);
		add(graveteiro);
		
		transcendenteJogador = new Transcendente();
		transcendenteJogador.setBounds(375, 460, 700, 100);
		add(transcendenteJogador);
		
		botaozeiro = new Botaozeiro(tratadoraDeEvento);
		botaozeiro.setBounds(75, 610, 1000, 25);
		add(botaozeiro);
		
		status = new Status();
		status.setBounds(75, 645, 1000, 50);
		add(status);
	}

	public void alterarConfiguracaoTabuleiro(ConfiguracaoTabuleiro confTab) {
		String corPeaoJogador = confTab.obterCorPeaoJogador();
		String corPeaoAdversario = confTab.obterCorPeaoAdversario();
		int qtdPeoesJogadorLiberados = confTab.obterQtdPeoesJogadorLiberados();
		int qtdPeoesAdversarioLiberados = confTab.obterQtdPeoesAdversarioLiberados();

		transcendenteAdversario.alterarConfiguracao(qtdPeoesAdversarioLiberados, corPeaoAdversario);
		tabuleiro.alterarConfiguracao(confTab.obterConteudoCasas());
		graveteiro.alterarConfiguracao(confTab.obterGravetosRiscados());
		transcendenteJogador.alterarConfiguracao(qtdPeoesJogadorLiberados, corPeaoJogador);
		botaozeiro.alterarConfiguracao(confTab.obterNumeroCasaPeaoLiberacao());
		status.alterarConfiguracao(confTab.obterPontuacao(), confTab.obterNomeJogadorDaVez());
	}
}
