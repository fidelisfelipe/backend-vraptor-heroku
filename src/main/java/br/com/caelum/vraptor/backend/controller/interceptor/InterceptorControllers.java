package br.com.caelum.vraptor.backend.controller.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.util.ControllerUtil;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;

@Intercepts
public class InterceptorControllers implements Interceptor {

	private Result result;
	private HttpServletRequest req;

	@Deprecated
	protected InterceptorControllers(){
		this(null, null);
	}
	
	/**
	 * @param result
	 * @param contexto
	 * @param req
	 */
	@Inject
	public InterceptorControllers(Result result, HttpServletRequest req) {
		this.result = result;
		this.req = req;
	}
	
	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method,
			Object controllerInstance) throws InterceptionException {

		stack.next(method, controllerInstance);
	}

	@Override
	public boolean accepts(ControllerMethod method) {
		return true;
	}

}