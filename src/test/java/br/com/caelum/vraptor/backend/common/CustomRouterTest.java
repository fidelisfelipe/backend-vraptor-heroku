package br.com.caelum.vraptor.backend.common;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.backend.GenericTest;
import br.com.caelum.vraptor.backend.common.CustomRouter;
import br.com.caelum.vraptor.controller.BeanClass;
import br.com.caelum.vraptor.controller.DefaultBeanClass;
import br.com.caelum.vraptor.controller.DefaultControllerMethod;

/**
 * @author fidelis.guimaraes
 *
 */
public class CustomRouterTest extends GenericTest {

	private CustomRouterMock customRouter;

	class ControllerTest {
		@Get
		public void get() {
		}

		@Post
		public void post() {
		}

		@Delete
		public void delete() {
		}

		@Put
		public void put() {
		}
	}

	class CustomRouterMock extends CustomRouter {
		@Override
		protected String[] getURIsFor(Method javaMethod, Class<?> type) {
			return new String[] { "" };
		}
	}

	@Before
	public void setUp() {
		customRouter = spy(new CustomRouterMock());

	}

	@Test
	public void testMethodsAnnotations() throws NoSuchMethodException, SecurityException {
		BeanClass beanClass = new DefaultBeanClass(ControllerTest.class);

		Map<String, String> mapaRotas = new HashMap<String, String>();
		for (Method method : ControllerTest.class.getDeclaredMethods()) {

			DefaultControllerMethod controllerMethod = spy(new DefaultControllerMethod(beanClass, method));

			when(customRouter.getURIsFor(any(Method.class), any(Class.class)))
					.thenReturn(new String[] { "URI:/" + method.getName() });

			mapaRotas = customRouter.getURIsForMethod(controllerMethod);

		}
		assertTrue("o mapa de rotas deve ser preenchido", mapaRotas.size() > 0);
	}

}
