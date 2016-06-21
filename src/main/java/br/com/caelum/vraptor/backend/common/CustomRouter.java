package br.com.caelum.vraptor.backend.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.ReflectionProvider;
import br.com.caelum.vraptor.http.route.PathAnnotationRoutesParser;
import br.com.caelum.vraptor.http.route.Router;
@ApplicationScoped
@Specializes
public class CustomRouter extends PathAnnotationRoutesParser {
	
	private static Logger log;

	protected CustomRouter() {
		this(null, null);
	}
	@Inject
	public CustomRouter(Router router, ReflectionProvider reflectionProvider) {
		super(router, reflectionProvider);
		 log = Logger.getLogger(getClass());
	}
	
	@Override
	protected String[] getURIsFor(Method javaMethod, Class<?> type) {
		return super.getURIsFor(javaMethod, type);
	}
	
	public Map<String, String> getURIsForMethod(ControllerMethod method) {
		Map<String,String> mapaRotas = new HashMap<String, String>();
		
		log.info("Interceptando Rotas da funcionalidade...");
		log.info("Classe: "+method.getMethod().getDeclaringClass().getName());
		log.info("Metodo: "+method.getMethod().getName());
		
		
		
		log.info("URI's:");
		
		String [] lista = getURIsFor(method.getMethod(), method.getController().getClass());
		String uri = "";
		for (String string : lista) {
			uri = string.replace("URI:", "").trim();
			
			if(method.containsAnnotation(Get.class)){
				mapaRotas.put(Get.class.getSimpleName()+uri, Get.class.getSimpleName());
				log.info(Get.class.getSimpleName());
		    }else
		    if(method.containsAnnotation(Post.class)){
		    	mapaRotas.put(Post.class.getSimpleName()+uri, Post.class.getSimpleName());
		    	log.info(Post.class.getSimpleName());
		    }else
		    if(method.containsAnnotation(Delete.class)){
		    	mapaRotas.put(Delete.class.getSimpleName()+uri, Delete.class.getSimpleName());
		    	log.info(Delete.class.getSimpleName());
		    }else
		    if(method.containsAnnotation(Put.class)){
		    	mapaRotas.put(Put.class.getSimpleName()+uri, Put.class.getSimpleName());
		    	log.info(Put.class.getSimpleName());
		    }
			log.info(uri);
			
		}
		
		log.info("Rotas interceptadas com sucesso!");
		
		return mapaRotas;
	}

}
