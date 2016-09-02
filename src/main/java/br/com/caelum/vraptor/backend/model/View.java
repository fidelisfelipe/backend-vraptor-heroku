package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class View implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String templateUrl;
	private String controller;

	private Date data = new Date();
	private Date atualizacao = new Date();
	
	@Id
	@SequenceGenerator(name="seq_view", sequenceName="seq_view", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_view")
	@Column(name="id_view", nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	
}
