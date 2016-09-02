package br.com.caelum.vraptor.backend.dao.impl;

import javax.inject.Inject;

import org.hibernate.Session;

import br.com.caelum.vraptor.backend.model.Menu;

public class DefaultMenuDao extends DefaultGenericDao<Menu> {

	protected DefaultMenuDao() {
		this(null);
	}
	@Inject
	public DefaultMenuDao(Session session) {
		super(session);
	}

}
