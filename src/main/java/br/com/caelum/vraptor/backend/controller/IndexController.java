package br.com.caelum.vraptor.backend.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;

@Controller
public class IndexController {

	private final Result result;
	private HttpServletRequest req;
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
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Get
	@Path("/")
	public void index() {
		result.use(Results.json()).from("VRaptor!", "user").serialize();
	}
	
}