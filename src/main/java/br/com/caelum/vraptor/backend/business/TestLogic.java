package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.dao.impl.DefaultTestDao;
import br.com.caelum.vraptor.backend.model.Test;

public class TestLogic {
	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	
	private DefaultTestDao tests;
	
	protected TestLogic() {
		this(null);
	}
	
	@Inject
	public TestLogic (DefaultTestDao tests){
		this.tests = tests;
	}
	
	public void persist(Test t) {
		this.tests.persist(t);
	}
	
	public void update(Test t) {
		tests.update(t);
	}
	
	public void saveOrUpdate(Test t){
		tests.saveOrUpdate(t);
	}
	
	public void delete(Test t){
		tests.delete(t);
	}
	
	public List<Test> listAll(){
		return tests.listAll();
	}
	
	public Test load(Test t){
		return tests.load(t.getId());
	}
}
