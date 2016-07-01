package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.business.exception.NegocioException;
import br.com.caelum.vraptor.backend.dao.impl.DefaultRecursoDao;
import br.com.caelum.vraptor.backend.model.Recurso;

/**
 * @author fidelis.guimaraes
 *
 */
public class RecursoLogic {

	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	private DefaultRecursoDao recursos;
	
	protected RecursoLogic() {
	}
	@Inject
	public RecursoLogic(DefaultRecursoDao recursos){
		this.recursos = recursos;
	}
	
	public Recurso load(long id) {
		return recursos.load(id);
	}
	
	public void update(Recurso recurso) {
		recursos.update(recurso);
	}

	public void persist(Recurso recurso) {
		recursos.persist(recurso);
	}

	public void delete(Recurso recurso) {
		recursos.delete(recurso);
	}

	public List<Recurso> listAll() {
		return recursos.listAll();
	}
	
	public Recurso existe(Recurso recurso){
		return recursos.existe(recurso);
	}
	
	public void remove(Recurso recurso) {
		recursos.delete(recurso);
	}
	public void refresh(Recurso recurso) {
		recursos.refresh(recurso);
	}
	public void verificarDadosOrigatoriosDefault(Recurso recurso) throws NegocioException {
		if(recurso.getNome() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
		if(recurso.getDescricao() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
		if(recurso.getUri() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
		if(recurso.getActionMethod() == null){
			throw new NegocioException(INFORME_O_CAMPO_OBRIGATORIO);
		}
	}
	
	
}
