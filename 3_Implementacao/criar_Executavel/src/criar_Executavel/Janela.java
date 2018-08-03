package criar_Executavel;
import javax.swing.*;
import java.awt.event.*;

public class Janela extends JPanel {
	
	Janela() {
		setSize(100,100);
		
		JLabel mensagem = new JLabel("Clique no botão!");
		JButton botao = new JButton("clique-me");
		
		Tratadora_Botao trata_Botao = new Tratadora_Botao(mensagem);
		botao.addActionListener(trata_Botao);
		
		add(mensagem);
		add(botao);
		
	}
	
	class Tratadora_Botao implements ActionListener {
		private JLabel mensagem;
		
		Tratadora_Botao(JLabel mensagem) {
			this.mensagem = mensagem;
		}
		
		public void actionPerformed(ActionEvent event) {
			mensagem.setText("Clicou!");
		}
	}
	
}
