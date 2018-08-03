package interfaceGrafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Peaozeiro extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected Casa[] casasDePeao;
	
	Peaozeiro(int numeroDePeoes, int lin, int col) {
		configurarPeaozeiro(numeroDePeoes, lin, col);
		carregarComponentes();
	}
	
	public void configurarPeaozeiro(int numeroDePeoes, int lin, int col) {
		setLayout(new GridLayout(lin,col));
		casasDePeao = new Casa[numeroDePeoes];
	}
	
	public void carregarComponentes() {
		for (int i = 0; i < casasDePeao.length; i++) {
			Casa casa = new Casa();
			casa.setPreferredSize(new Dimension(100,100));
			casa.setOpaque(true);
			casa.setHorizontalAlignment(SwingConstants.CENTER);
			casasDePeao[i] = casa;
			add(casa);
		}
	}
	
	public JLabel[] getCasasTabuleiro() {
		return casasDePeao;
	}
}
