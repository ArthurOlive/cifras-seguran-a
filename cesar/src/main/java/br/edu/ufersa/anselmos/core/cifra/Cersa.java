package br.edu.ufersa.anselmos.core.cifra;

public class Cersa implements Cifra {
	private int deslocate;
	
	public Cersa (int deslocate) {
		this.deslocate = deslocate % 256;
	}

	public String encriptar(String str) {
		
		String encript = "";
		for (int i = 0; i < str.length(); i++) {
			encript += (char) ((str.charAt(i) + deslocate) % 256);
		}
		
		return encript;
	}

	public String decriptar(String str) {
		
		String decript = "";
		for (int i = 0; i < str.length(); i++) {
			decript += (char) ((str.charAt(i) - deslocate) % 256);
		}
		
		return decript;
	}
}
