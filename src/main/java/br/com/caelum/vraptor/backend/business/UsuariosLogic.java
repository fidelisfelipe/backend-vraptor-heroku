package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultUsuarioDao;
import br.com.caelum.vraptor.backend.model.Usuario;
import br.com.caelum.vraptor.backend.model.UsuarioWeb;

public class UsuariosLogic {

	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private DefaultUsuarioDao usuarios;
	
	protected UsuariosLogic() {
	}
	@Inject
	public UsuariosLogic(DefaultUsuarioDao usuarios){
		this.usuarios = usuarios;
	}
	
	public Usuario load(long id) {
		return usuarios.load(id);
	}
	
	public void update(Usuario usuario) {
		usuarios.update(usuario);
	}

	public void persist(Usuario usuario) {
		usuarios.persist(usuario);
	}

	public void delete(Usuario usuario) {
		usuarios.delete(usuario);
	}

	public List<Usuario> listAll() {
		return usuarios.listAll();
	}
	
	public Usuario existe(Usuario usuario){
		return usuarios.existe(usuario);
	}
	
	public void logar(Usuario usuario, UsuarioWeb usuarioWeb) {
		usuarioWeb.setLogon(usuario);	
	}
	public void remove(Usuario usuario) {
		usuarios.delete(usuario);
	}
	public void refresh(Usuario usuario) {
		usuarios.refresh(usuario);
	}
	public void verificarDadosOrigatoriosDefault(Usuario usuario) throws NegocioException {
		if(usuario.getNome() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
		if(usuario.getEmail() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
		if(usuario.getSenha() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
	}
	
	
}
