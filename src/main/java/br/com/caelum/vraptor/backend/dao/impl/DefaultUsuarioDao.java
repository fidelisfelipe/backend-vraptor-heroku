package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.dao.UsuarioDao;
import br.com.caelum.vraptor.backend.model.Usuario;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

/**
 * @author fidelis.guimaraes
 *
 */
public class DefaultUsuarioDao extends DefaultGenericDao<Usuario> implements
		UsuarioDao {
	
	public DefaultUsuarioDao() {
		this(null);
	}
	
	@Inject
	protected DefaultUsuarioDao(Session session) {
		super(session);
	}

	/**
	 * Verifica se existe o usuário informado e ativo
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario existe(Usuario usuario) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(usuario);
		return (Usuario) getSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("senha", usuario.getSenha()))
				.add(Restrictions.eq("ativo", true)).uniqueResult();
	}
}