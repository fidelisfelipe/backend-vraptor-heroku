package br.com.caelum.vraptor.backend.controller;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.HttpMethod;
import br.com.caelum.vraptor.http.route.Route;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;

@Controller
public class IndexController {

	private final Result result;
	private HttpServletRequest req;
	@Inject
	private ServletContext context;
	@Inject
	private Router router;
	/**
	 * @deprecated CDI eyes only
	 */
	protected IndexController() {
		this(null, null);
	}
	
	@Inject
	public IndexController(Result result, HttpServletRequest req) {
		this.result = result;
		this.req = req;
	}
	/**
	 * Method of test for Backend online
	 */
	@Get
	@Path("/")
	public void test() {
		result.use(Results.json()).from("Backend Working!", "user").serialize();
	}
	class Features {
		private String function = new String();
		private String rota = new String();
		private String method = new String();
	}
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/features")
	public void features() {
		List<Route> routes = router.allRoutes();
		List<Features> features = new ArrayList<Features>();
		
		for (Route route : routes) {
				Features feature = new Features();
				feature.function = route.getControllerMethod().getController().getType().getName().replaceAll("^br.*.controller.", "").replaceAll("Controller$", "");
				feature.rota = route.getOriginalUri();
				if(route.allowedMethods().size() == 1) {
				feature.method = route.allowedMethods().toArray()[0].toString();
				} else {
					for (HttpMethod method : route.allowedMethods()) {
						feature.method += method.toString() + " ";
					}
					feature.method.trim();
				}
				features.add(feature);
		}

		result.use(Results.json()).from(features, "features").serialize();
	}
	
	
	
}