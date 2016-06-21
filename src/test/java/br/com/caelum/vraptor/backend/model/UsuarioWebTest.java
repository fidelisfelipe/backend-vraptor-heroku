package br.com.caelum.vraptor.backend.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.backend.GenericTest;
import br.com.caelum.vraptor.backend.model.Perfil;
import br.com.caelum.vraptor.backend.model.Recurso;
import br.com.caelum.vraptor.backend.model.Usuario;
import br.com.caelum.vraptor.backend.model.UsuarioWeb;


/**
 * @author fidelis.guimaraes
 *
 */
public class UsuarioWebTest extends GenericTest{
	
	@Before
	public void setUp(){
	}
	
	@Test
	public void testLogonComoMaster(){
		//give
		UsuarioWeb usuarioWeb = new UsuarioWeb();
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setNome("Raul Seixas");
		Perfil perfil = new Perfil();
		perfil.setId(1l);
		perfil.setNome("Master");
		perfil.setDescricao("Perfil do Master");
		perfil.setAtivo(Boolean.TRUE);
		perfil.setMaster(Boolean.TRUE);
		List<Recurso> recursos = new ArrayList<Recurso>();
		Recurso recurso = new Recurso();
		recurso.setAtivo(Boolean.TRUE);
		recurso.setActionMethod("GET");
		recurso.setUri("/test");
		recursos.add(recurso);
		perfil.setRecursos(recursos );
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis.add(perfil);
		usuario.setPerfis(perfis);
		
		//when
		usuarioWeb.setLogon(usuario);
		
		//then
		assertTrue("deve estar logado",usuarioWeb.isLogado());
		assertTrue("deve ser Master",usuarioWeb.isMaster());
		assertTrue("deve conter o recurso do perfil", usuarioWeb.getRouterMap().containsKey(recurso.getActionMethod()+recurso.getUri()));
		assertEquals("deve conter o recurso do perfil",recurso.getActionMethod(), usuarioWeb.getRouterMap().get(recurso.getActionMethod()+recurso.getUri()));
	}
	
	@Test
	public void testLogonFalhaQndNaoHaUmRecursoAtivo(){
		//give
		UsuarioWeb usuarioWeb = new UsuarioWeb();
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setNome("Raul Seixas");
		Perfil perfil = new Perfil();
		perfil.setId(1l);
		perfil.setNome("Master");
		perfil.setDescricao("Perfil do Master");
		perfil.setAtivo(Boolean.TRUE);
		perfil.setMaster(Boolean.TRUE);
		List<Recurso> recursos = new ArrayList<Recurso>();
		Recurso recurso = new Recurso();
		recurso.setAtivo(Boolean.FALSE);
		recurso.setActionMethod("GET");
		recurso.setUri("/test");
		recursos.add(recurso);
		perfil.setRecursos(recursos );
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis.add(perfil);
		usuario.setPerfis(perfis);
		
		//when
		usuarioWeb.setLogon(usuario);
		
		//then
		assertFalse("deve estar deslogado",usuarioWeb.isLogado());
		assertTrue("deve ser Master",usuarioWeb.isMaster());
		assertFalse("deve não conter o recurso do perfil", usuarioWeb.getRouterMap().containsKey(recurso.getActionMethod()+recurso.getUri()));
	}
	@Test
	public void testLogonFalhaQndNaoHaUmPerfilAtivo(){
		//give
		UsuarioWeb usuarioWeb = new UsuarioWeb();
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setNome("Raul Seixas");
		Perfil perfil = new Perfil();
		perfil.setId(1l);
		perfil.setNome("Master");
		perfil.setDescricao("Perfil do Master");
		perfil.setAtivo(Boolean.FALSE);
		List<Perfil> perfis = new ArrayList<Perfil>();
		perfis.add(perfil);
		usuario.setPerfis(perfis);
		
		//when
		usuarioWeb.setLogon(usuario);
		
		//then
		assertFalse("deve estar deslogado",usuarioWeb.isLogado());
		assertFalse("deve não ser Master",usuarioWeb.isMaster());
		assertTrue("deve não conter recursos", usuarioWeb.getRouterMap().isEmpty());
	}
	
	@Test
	public void testLogout(){
		//give
		HttpSession httpSession = mock(HttpSession.class);
		UsuarioWeb usuarioWeb = new UsuarioWeb(httpSession);
		
		//when
		usuarioWeb.setLogout();
		
		//then
		verify(httpSession).invalidate();
		assertFalse(usuarioWeb.isLogado());
	}
	
	@Test
	public void testRecuperaUsuarioAposLogon(){
		//give
				UsuarioWeb usuarioWeb = new UsuarioWeb();
				Usuario usuario = new Usuario();
				usuario.setId(1l);
				usuario.setNome("Raul Seixas");
				Perfil perfil = new Perfil();
				perfil.setId(1l);
				perfil.setNome("Master");
				perfil.setDescricao("Perfil do Master");
				perfil.setAtivo(Boolean.TRUE);
				perfil.setMaster(Boolean.TRUE);
				List<Recurso> recursos = new ArrayList<Recurso>();
				Recurso recurso = new Recurso();
				recurso.setAtivo(Boolean.TRUE);
				recurso.setActionMethod("GET");
				recurso.setUri("/test");
				recursos.add(recurso);
				perfil.setRecursos(recursos );
				List<Perfil> perfis = new ArrayList<Perfil>();
				perfis.add(perfil);
				usuario.setPerfis(perfis);
				
				//when
				usuarioWeb.setLogon(usuario);
				
				//then
				
				Usuario usuarioRecuperado = usuarioWeb.getUsuario();
				
				assertEquals(usuario.getId(), usuarioRecuperado.getId());
				assertEquals(usuario.getNome(), usuarioRecuperado.getNome());
	}
	
}
