package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author fidelis.guimaraes
 *
 */

@Entity
public class Menu implements Serializable{
	/**
	 * 
	INSERT INTO menu(atualizacao, cadastro, title, name, show, url)
	    VALUES (current_date, current_date, 'Home', 'main.home', 'currentUser.isAuthorized', '/home');

	INSERT INTO view(atualizacao, cadastro, name, templateUrl, controller)
	    VALUES (current_date, current_date, 'pageContent', 'main/templates/home.html', 'HomeCtrl as ctrl');

	INSERT INTO ta_view_menu (id_menu, id_view) VALUES (1,1);
	 *
	 */

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String name;
	private String show;
	private String url;
	private List<View> views;
	private Date data = new Date();
	private Date atualizacao = new Date();
	
	@Id
	@SequenceGenerator(name="seq_menu", sequenceName="seq_menu", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_menu")
	@Column(name="id_menu",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "cadastro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ta_menu_view", joinColumns={@javax.persistence.JoinColumn(name="id_menu")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="id_view")})
	public List<View> getViews() {
		return views;
	}
	public void setViews(List<View> views) {
		this.views = views;
	}
	
}
