package br.com.caelum.vraptor.backend.controller;

import java.util.Set;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Options;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.HttpMethod;
import br.com.caelum.vraptor.events.VRaptorRequestStarted;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.view.Results;

@Controller
public class CORSController {

	private Result result;
	private Router router;
	private HttpServletResponse response;

	/**
	 * @deprecated
	 */
	public CORSController() {
	}

	@Inject
	public CORSController(Result result, Router router, HttpServletResponse response) {
		this.result = result;
		this.router = router;
		this.response = response;
	}
	@Consumes("application/json")
	@Options
	@Path({ "/*" })
	public void options(@Observes VRaptorRequestStarted requestInfo) {
		config(requestInfo);
	}

	private void config(VRaptorRequestStarted requestInfo) {
		if(response.getHeader("Access-Control-Allow-Origin") == null) {
		String origin = requestInfo.getRequest().getHeader("origin") != null
				? requestInfo.getRequest().getHeader("origin") : "*";

		result.use(Results.status()).header("Access-Control-Allow-Origin", origin);
		}
		Set<HttpMethod> allowed = router.allowedMethodsFor(requestInfo.getRequest().getRequestedUri());
		String allowMethods = allowed.toString().replaceAll("\\[|\\]", "");
		result.use(Results.status()).header("Allow", allowMethods);
		result.use(Results.status()).header("Access-Control-Allow-Methods", allowMethods);
		result.use(Results.status()).header("Access-Control-Allow-Headers",
				"Content-Type, X-Requested-With, accept, Authorization, origin");
	}

}
