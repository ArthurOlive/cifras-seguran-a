package br.edu.ufersa.anselmos.core.cifra;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.edu.ufersa.anselmos.core.cifra.Cersa;


public class CersaImpl {
	
	@Test
	public void teste01 () {
		String str = "abc";
		int deslocate = 1;
		
		Cersa c = new Cersa(deslocate);
		
		assertEquals("bcd", c.encriptar(str));
	}
	
	@Test
	public void teste02 () {
		String str = "ABC";
		int deslocate = 1;
		
		Cersa c = new Cersa(deslocate);
		
		String res = c.encriptar(str);
		assertEquals("BCD", c.encriptar(str));
		
		assertEquals(str, c.decriptar(res));
	}
	
	@Test
	public void teste03 () {
		String str = "arthur";
		int deslocate = 2;
		
		Cersa c = new Cersa(deslocate);
		
		String res = c.encriptar(str);
		assertEquals("ctvjwt", c.encriptar(str));
		
		assertEquals(str, c.decriptar(res));
	}
}
