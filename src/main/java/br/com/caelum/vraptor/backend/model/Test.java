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
public class Test implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty
	private String descricao;
	private Date data = new Date();
	private Date atualizacao = new Date();
	
	@Id
	@SequenceGenerator(name="seq_test", sequenceName="seq_test", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_test")
	@Column(name="id_test",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 10, nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
}
