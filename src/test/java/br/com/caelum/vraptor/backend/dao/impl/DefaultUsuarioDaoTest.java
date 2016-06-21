package br.com.caelum.vraptor.backend.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.caelum.vraptor.backend.GenericTest;
import br.com.caelum.vraptor.backend.dao.impl.DefaultUsuarioDao;
import br.com.caelum.vraptor.backend.model.Usuario;

/**
 * @author fidelis.guimaraes
 *
 */
public class DefaultUsuarioDaoTest extends GenericTest {

	@Mock
	private Session session;
	@Mock
	private Criteria criteria;
	
	
	@Before
	public void setUp(){
	}

	@Test
	public void testExistFalse(){
		//given
		DefaultUsuarioDao daoTest = new DefaultUsuarioDao(session);
		Usuario usuario = new Usuario();
		usuario.setNome("nome");
		//deve ser passado para criteria.add
		SimpleExpression seEmail = Restrictions.eq("email", usuario.getEmail());
		SimpleExpression seSenha = Restrictions.eq("senha", usuario.getSenha());
		SimpleExpression seAtivo = Restrictions.eq("ativo", Boolean.TRUE);
		
		//when
		when(session.createCriteria(daoTest.getPersistentClass())).thenReturn(criteria);
		when(criteria.add(any(seEmail.getClass()))).thenReturn(criteria);
		when(criteria.add(any(seSenha.getClass()))).thenReturn(criteria);
		when(criteria.add(any(seAtivo.getClass()))).thenReturn(criteria);
		when(criteria.uniqueResult()).thenReturn(null);
		
		//then
		Usuario exists = daoTest.existe(usuario);
		
		assertNull("deve conter algo",exists);
		
		verify(session).createCriteria(daoTest.getPersistentClass());
		verify(criteria, times(3)).add(any(seEmail.getClass()));
		verify(criteria).uniqueResult();
		
	}
	@Test
	public void testExistTrue(){
		//given
		DefaultUsuarioDao daoTest = new DefaultUsuarioDao(session);
		Usuario usuario = new Usuario();
		usuario.setNome("nome");
		//deve ser passado para criteria.add
		SimpleExpression seEmail = Restrictions.eq("email", usuario.getEmail());
		SimpleExpression seSenha = Restrictions.eq("senha", usuario.getSenha());
		SimpleExpression seAtivo = Restrictions.eq("ativo", Boolean.TRUE);

		//when
		when(session.createCriteria(daoTest.getPersistentClass())).thenReturn(criteria);
		when(criteria.add(any(seEmail.getClass()))).thenReturn(criteria);
		when(criteria.add(any(seSenha.getClass()))).thenReturn(criteria);
		when(criteria.add(any(seAtivo.getClass()))).thenReturn(criteria);
		when(criteria.uniqueResult()).thenReturn(new Usuario());
		
		//then
		Usuario exists = daoTest.existe(usuario);
		
		assertNotNull("deve conter algo",exists);
		
		verify(session).createCriteria(daoTest.getPersistentClass());
		verify(criteria, times(3)).add(any(seEmail.getClass()));
		verify(criteria).uniqueResult();
		
	}
	//then
	@Test(expected = IllegalArgumentException.class)
	public void testExistFailIllegalArgumentException(){
		//when
		new DefaultUsuarioDao(session).existe(null);
	}
	
}
