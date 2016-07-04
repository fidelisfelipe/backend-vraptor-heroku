package br.com.caelum.vraptor.backend.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.TestLogic;
import br.com.caelum.vraptor.backend.model.Test;
import br.com.caelum.vraptor.view.Results;

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
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void testAdd(Test test) {
		this.logic.persist(test);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path({"", "/"})
	public void testList() {
		this.result.use(Results.json()).from(this.logic.listAll(), "tests").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path("/{test.id}")
	public void testLoad(Test test) {
		this.result.use(Results.json()).from(this.logic.load(test), "test").serialize();
	}
	@Consumes("application/json")
	@Put
	@Path("/{test.id}")
	public void testUpdate(Test test) {
		this.logic.update(test);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Transactional
	@Consumes("application/json")
	@Delete
	@Path("/{test.id}")
	public void testRemove(Test test) {
		this.logic.delete(test);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}
