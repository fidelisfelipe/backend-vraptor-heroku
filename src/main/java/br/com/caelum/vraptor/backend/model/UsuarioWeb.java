package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.backend.util.PreconditionUtil;


/**
 * @author fidelis.guimaraes
 *
 */
@Named("usuarioWeb")
@SessionScoped
public class UsuarioWeb implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private boolean logado;
	private Long id;
	private String nome;
	private Map<String, String> routerMap;
	private List<Long> idPerfis;
	private boolean isMaster;
	
	private HttpSession httpSession;
	public UsuarioWeb() {
	}
	@Inject
	public UsuarioWeb(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public void setLogout() {
		this.httpSession.invalidate();
	}
	public void setLogon(Usuario usuario) {
		//pre-reqs
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(usuario);
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(usuario.getId());
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(usuario.getNome());
		PreconditionUtil.isNotEmptyDoThrowsIllegalArgumentException(usuario.getPerfis());
		
		nome = usuario.getNome();
		routerMap = new HashMap<String, String>();
		
		id = usuario.getId();
		idPerfis = new ArrayList<Long>();
		
		for (Perfil perfil : usuario.getPerfis()) {
			if (perfil.getAtivo()) {
				
				isMaster = perfil.isMaster();
				
				idPerfis.add(perfil.getId());
				for (Recurso recurso : perfil.getRecursos()) {
					if (recurso.getAtivo()) {
						routerMap.put(recurso.getActionMethod()+recurso.getUri(), recurso.getActionMethod());
						this.logado = true;
					}
				}
			}
		}
		
	}

	public boolean isLogado() {
		return logado;
	}
	
	public Usuario getUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		return usuario;
	}

	/**
	 * Gets and Sets
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, String> getRouterMap() {
		return routerMap;
	}

	public List<Long> getIdPerfis() {
		return idPerfis;
	}

	public void setIdPerfis(List<Long> idPerfis) {
		this.idPerfis = idPerfis;
	}

	public boolean isMaster() {
		return isMaster;
	}
	
}
