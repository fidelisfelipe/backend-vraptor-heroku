package br.com.caelum.vraptor.backend.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.backend.dao.RecursoDao;
import br.com.caelum.vraptor.backend.model.Recurso;
import br.com.caelum.vraptor.backend.util.PreconditionUtil;

/**
 * @author fidelis.guimaraes
 *
 */
public class DefaultRecursoDao extends DefaultGenericDao<Recurso> {
	
	/**
	 * @deprecated CDI eyes only
	 */	
	protected DefaultRecursoDao() {
		this(null);
	}
	
	@Inject
	public DefaultRecursoDao(Session session) {
		super(session);
	}
	
	/**
	 * Verifica se existe o recurso informado e ativo
	 * 
	 * @param recurso
	 * @return
	 */
	public Recurso existe(Recurso recurso) {
		PreconditionUtil.isNotNullDoThrowsIllegalArgumentException(recurso);
		return (Recurso) getSession().createCriteria(Recurso.class)
				.add(Restrictions.eq("nome", recurso.getNome()))
				.add(Restrictions.eq("descricao", recurso.getDescricao()))
				.add(Restrictions.eq("uri", recurso.getUri()))
				.add(Restrictions.eq("actionMethod", recurso.getActionMethod()))
				.add(Restrictions.eq("ativo", true)).uniqueResult();
	}

}