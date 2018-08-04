package baita_Tipo;

class Pessoa {
	protected byte estado;

	Pessoa (byte estado) {
		this.estado = estado;
	}
	
	public byte get_estado() {
		return estado;
	}
}

public class Main {
	public static void main(String args[]) {
		Pessoa p = new Pessoa((byte) 0);
		
		System.out.println("Estado p é = " + p.get_estado());
	}
}
