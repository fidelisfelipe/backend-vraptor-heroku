package br.com.caelum.vraptor.backend.dao.impl;

import org.hibernate.Session;

import br.com.caelum.vraptor.backend.model.Test;

public class DefaultTestDao extends DefaultGenericDao<Test> {

	protected DefaultTestDao() {
		this(null);
	}
	
	public DefaultTestDao(Session session) {
		super(session);
	}

}
