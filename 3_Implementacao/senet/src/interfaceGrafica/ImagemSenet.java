package interfaceGrafica;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImagemSenet {
	private ImageIcon imagem;
	
	public ImagemSenet(String enderecoImagem) {
		imagem = new ImageIcon(getClass().getResource(enderecoImagem));
	}
	
	public ImagemSenet(String enderecoImagem, int w, int h) {
		imagem = new ImageIcon(getClass().getResource(enderecoImagem));
		redimensionarImagem(w, h);
	}
	
	public void redimensionarImagem(int w, int h) {
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(w, h,  Image.SCALE_DEFAULT));
	}
	
	public ImageIcon getImagem() {
		return imagem;
	}
}
