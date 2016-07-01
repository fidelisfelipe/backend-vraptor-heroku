package br.com.caelum.vraptor.backend.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.TestLogic;
import br.com.caelum.vraptor.backend.model.Test;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/tests")
@Controller
public class TestController {

	private Result result;
	private TestLogic logic;

	protected TestController() {
	}
	
	@Inject
	public TestController (Result result, TestLogic logic){
		this.result = result;
		this.logic = logic;
	}
	
	@Post
	@Path("/add/post")
	public void testAdd(Test t) {
		this.logic.persist(t);
	}
	
}
