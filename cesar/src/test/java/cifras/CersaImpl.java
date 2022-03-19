package cifras;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CersaImpl {
	
	@Test
	public void teste01 () {
		String str = "abc";
		int deslocate = 1;
		
		Cersa c = new Cersa(deslocate);
		
		assertEquals("bcd", c.encript(str));
	}
	
	@Test
	public void teste02 () {
		String str = "ABC";
		int deslocate = 1;
		
		Cersa c = new Cersa(deslocate);
		
		String res = c.encript(str);
		assertEquals("BCD", c.encript(str));
		
		assertEquals(str, c.decript(res));
	}
	
	@Test
	public void teste03 () {
		String str = "arthur";
		int deslocate = 2;
		
		Cersa c = new Cersa(deslocate);
		
		String res = c.encript(str);
		assertEquals("ctvjwt", c.encript(str));
		
		assertEquals(str, c.decript(res));
	}
}
