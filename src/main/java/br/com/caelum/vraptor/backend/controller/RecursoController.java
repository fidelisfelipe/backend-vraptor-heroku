package br.com.caelum.vraptor.backend.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.RecursoLogic;
import br.com.caelum.vraptor.backend.model.Recurso;
import br.com.caelum.vraptor.view.Results;

/**
 * @author fidelis.guimaraes
 *
 */
@Controller
@Path("/recursos")
public class RecursoController {
	private final Result result;
	private final RecursoLogic logic;
	
	protected RecursoController() {
		this(null, null);
	}

	@Inject
	public RecursoController(Result result, RecursoLogic logic){
		this.result = result;
		this.logic = logic;
	}
	@Transactional
	@Consumes(value="application/json")
	@Post
	@Path("/novo")
	public void novo(Recurso recurso) {
		result.on(HibernateException.class).use(Results.json()).from("fail").serialize();
		logic.persist(recurso);
		result.use(Results.json()).from(recurso, "recurso").serialize();
	}

}