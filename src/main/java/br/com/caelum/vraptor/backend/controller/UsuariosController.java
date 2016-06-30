package br.com.caelum.vraptor.backend.controller;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.hibernate.HibernateException;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.backend.business.UsuariosLogic;
import br.com.caelum.vraptor.backend.model.Usuario;
import br.com.caelum.vraptor.view.Results;
@Path("/usuarios")
@Controller
public class UsuariosController {

	private final Result result;
	private final UsuariosLogic logic;
	private final ServletContext contexto;
	private final ServletRequest req;
	/**
	 * @deprecated CDI eyes only
	 */
	protected UsuariosController() {
		this(null, null, null, null);
	}
	
	@Inject
	public UsuariosController(Result result, UsuariosLogic logic, ServletContext contexto, ServletRequest req) {
		this.result = result;
		this.logic = logic;
		this.contexto = contexto;
		this.req = req;
	}
	@Get
	@Path({"","/"})
	public void index() {
		result.include("usuarioList", logic.listAll());
	}
	@Delete
	@Path({"/remove/{usuario.id}"})
	public void remove(Usuario usuario) {
		result.on(HibernateException.class).forwardTo(this).fail();
		logic.delete(usuario);
		result.forwardTo(this).index();
	}
	@Consumes("application/json")
	@Post
	@Path("/novo")
	public void index(Usuario usuario){
		result.on(HibernateException.class).forwardTo(this).fail();
		logic.persist(usuario);
		result.forwardTo(this).index();
	}

	private void fail(){
		result.use(Results.json()).withoutRoot().from("fail").serialize();
		result.nothing();
	}
	@Get
	@Path("/{id}")
	public void index(Integer id){
	}
	
	@Get
	@Path("/json")
	public void listUsers(){
		result.use(Results.json()).withoutRoot().from(logic.listAll()).serialize();
	}
}