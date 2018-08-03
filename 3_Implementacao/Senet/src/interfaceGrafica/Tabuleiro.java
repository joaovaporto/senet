package interfaceGrafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;

public class Tabuleiro extends Peaozeiro {
	private static final long serialVersionUID = 1L;
	
	protected TratadoraDeEvento tratadoraDeEvento;
	
	public Tabuleiro(TratadoraDeEvento tratadoraDeEvento) {
		super(30, 3, 10);
		configurarTabuleiro(tratadoraDeEvento);
		configurarCasasTabuleiro();
		configurarBordasTabuleiro();
	}
	
	public void configurarTabuleiro(TratadoraDeEvento tratadoraDeEvento) {
		this.tratadoraDeEvento = tratadoraDeEvento;
		setPreferredSize(new Dimension(1000, 300));
	}
	
	public void configurarCasasTabuleiro() {
		for (int i = 0; i < 30; i++) {
			int j = i;
			
			if (i > 9 && i < 20)
				j = 29 - i;
			
			Casa casa = casasDePeao[i];
			casa.alterarNumeroDaCasa(j+1);
			
			casa.alterarCorDeFundo(new Color(215,157,120));
			
			if (i > 9 && i < 20)
				j = i + 1;
			
			if (j % 2 == 0)
				casa.alterarCorDeFundo(new Color(245,207,151));
			
			casa.setCursor(new Cursor(Cursor.HAND_CURSOR));
			casa.addMouseListener(tratadoraDeEvento);
		}
	}
	
	public void configurarBordasTabuleiro() {
		Color corBorda = Color.BLACK;
		MatteBorder borda;
		
		casasDePeao[0].setBorder(new MatteBorder(3, 3, 0, 3, corBorda));
		casasDePeao[10].setBorder(new MatteBorder(3, 3, 3, 3, corBorda));
		casasDePeao[20].setBorder(new MatteBorder(0, 3, 3, 3, corBorda));
		
		borda = new MatteBorder(3,0,0,3, corBorda);
		for (int i = 1; i < 10; i++)
			casasDePeao[i].setBorder(borda);
		
		borda = new MatteBorder(3,0,3,3, corBorda);
		for (int i = 11; i < 20; i++)
			casasDePeao[i].setBorder(borda);
		
		borda = new MatteBorder(0,0,3,3, corBorda);
		for (int i = 21; i < 30; i++)
			casasDePeao[i].setBorder(borda);
	}

	public void alterarConfiguracao(String[] conteudoCasas) {
		for (int i = 0; i < 30; i++) {
			Casa casa = casasDePeao[i];
			int j = i;
			
			if (i > 9 && i < 20)
				j = 29 - i;
			
			casa.alterarPeaoOcupante(conteudoCasas[j]);
		}
	}
}
