package br.com.caelum.vraptor.backend.controller;

import java.util.List;

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
import br.com.caelum.vraptor.backend.business.MenuLogic;
import br.com.caelum.vraptor.backend.model.Menu;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Path("/menus")
@Controller
public class MenuController {

	private Result result;
	private MenuLogic logic;

	protected MenuController() {
	}
	
	@Inject
	public MenuController (Result result, MenuLogic logic){
		this.result = result;
		this.logic = logic;
	}
	@Transactional
	@Consumes("application/json")
	@Post
	@Path("/add")
	public void add(Menu menu) {
		this.logic.persist(menu);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path({"", "/"})
	public void list() {
		List<Menu> menus = this.logic.listAll();
		this.result.use(Results.json()).withoutRoot().from(menus).include("views").serialize();
	}
	@Consumes("application/json")
	@Get
	@Path("/{menu.id}")
	public void load(Menu menu) {
		this.result.use(Results.json()).from(this.logic.load(menu), "menu").serialize();
	}
	@Consumes("application/json")
	@Put
	@Path("/{menu.id}")
	public void update(Menu menu) {
		this.logic.update(menu);
		this.result.use(Results.json()).from("OK").serialize();
	}
	@Transactional
	@Consumes("application/json")
	@Delete
	@Path("/{menu.id}")
	public void remove(Menu menu) {
		this.logic.delete(menu);
		this.result.use(Results.json()).from("OK").serialize();
	}
	
}
