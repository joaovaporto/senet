package criar_Executavel;
import java.awt.BorderLayout;

import javax.swing.*;

public class Main {
	
	
	
	public static void main(String args[]) {
		JFrame janela = new JFrame("Minha janela");
		Janela frame = new Janela();
		
		janela.setVisible(true);
		janela.setSize(100, 100);
		janela.getContentPane().add(frame, BorderLayout.CENTER);
	}	
}