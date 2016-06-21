package br.com.caelum.vraptor.backend.annotations;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.backend.annotations.Restrito;
/**
 * Anotação que permite acesso sem login aos métodos
 * @author fidelis.guimaraes
 *
 */
public class RestritoTest {

	class RestritoMockClass{
		@Restrito
		public void test(){}
	}
	
	
	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testRestritoPresent(){
		boolean isMetodoAnotado = false;
		
		RestritoMockClass classe = new RestritoMockClass();
		for (Method method : classe.getClass().getMethods()) {
			if(method.isAnnotationPresent(Restrito.class)){
				isMetodoAnotado = true;
				break;
			}
		}
		assertTrue("metodo deve estar anotado", isMetodoAnotado);
	}
	
}
