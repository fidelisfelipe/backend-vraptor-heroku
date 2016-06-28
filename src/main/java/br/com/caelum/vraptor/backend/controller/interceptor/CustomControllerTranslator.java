package br.com.caelum.vraptor.backend.controller.interceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.http.DefaultControllerTranslator;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.http.route.Router;
@Specializes
@ApplicationScoped
public class CustomControllerTranslator extends DefaultControllerTranslator {
	
	private static Logger log;
	@Inject
	private Result result;
	@Deprecated
	protected CustomControllerTranslator() {
		super(null);
	}
	
	@Inject
	public CustomControllerTranslator(Router router) {
		super(router);
		log = Logger.getLogger(getClass());
	}
	
	@Override
	public ControllerMethod translate(MutableRequest request) {
		log.info("request interceptor - uri: " + request.getRequestURI());
		return super.translate(request);
	}
}
