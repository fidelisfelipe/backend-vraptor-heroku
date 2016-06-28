package br.com.caelum.vraptor.backend.controller.interceptor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

@Intercepts
@RequestScoped
public class InterceptorControllers{

	private static Logger log;

	@Deprecated
	public InterceptorControllers() {
		log = Logger.getLogger(getClass());
	}

	@BeforeCall
	public void before(){
		log.info("before request...");
	}
	
	@AfterCall
	public void after(){
		log.info("after request...");
	}
	
	@AroundCall
	public void intercept(SimpleInterceptorStack stack) throws InterceptionException {
		log.info("intercepted request...");
		stack.next();
	}

}