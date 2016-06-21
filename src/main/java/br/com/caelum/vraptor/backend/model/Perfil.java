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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Perfil implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	private List<Recurso> recursos;
	private boolean ativo;
	private Date data = new Date();
	private Date atualizacao = new Date();
	private boolean isMaster;
	

	@Id
	@SequenceGenerator(name="seq_perfil", sequenceName="seq_perfil", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_perfil")
	@Column(name="id_perfil",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length=80, nullable=false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(length=255)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ta_perfil_recurso", joinColumns={@javax.persistence.JoinColumn(name="id_perfil")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="id_recurso")})
	public List<Recurso> getRecursos() {
		return recursos;
	}
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	@Column(name ="ativo", nullable=false, columnDefinition = "BOOLEAN DEFAULT false")
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Column(name ="master", nullable=false, columnDefinition = "BOOLEAN DEFAULT false")
	public boolean isMaster() {
		return isMaster;
	}
	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}
	@Column(name ="cadastro", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	@Column(name ="atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAtualizacao() {
		return atualizacao;
	}
	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}