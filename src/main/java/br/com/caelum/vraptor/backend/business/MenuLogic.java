package br.com.caelum.vraptor.backend.business;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.backend.dao.impl.DefaultMenuDao;
import br.com.caelum.vraptor.backend.model.Menu;
//INSERT INTO menu(atualizacao, cadastro, title, name, show, url)
//VALUES (current_date, current_date, 'Home', 'main.home', 'currentUser.isAuthorized', '/home');

//INSERT INTO view(atualizacao, cadastro, name, templateUrl, controller)
//VALUES (current_date, current_date, 'pageContent', 'main/templates/home.html', 'HomeCtrl as ctrl');

//INSERT INTO ta_menu_view (id_menu, id_view) VALUES (1, 1);

public class MenuLogic {
	private static final String INFORME_O_CAMPO_OBRIGATORIO = "Informe o campo obrigatório";
	
	private DefaultMenuDao menus;
	
	protected MenuLogic() {
		this(null);
	}
	
	@Inject
	public MenuLogic (DefaultMenuDao menus){
		this.menus = menus;
	}
	
	public void persist(Menu object) {
		this.menus.persist(object);
	}
	
	public void update(Menu object) {
		menus.update(object);
	}
	
	public void saveOrUpdate(Menu object){
		menus.saveOrUpdate(object);
	}
	
	public void delete(Menu object){
		menus.delete(object);
	}
	
	public List<Menu> listAll(){
		return menus.listAll();
	}
	
	public Menu load(Menu object){
		return menus.load(object.getId());
	}
}
