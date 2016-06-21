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
public class Recurso implements Serializable {
	private static final long serialVersionUID = 4511172336615406359L;
	private Long id;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String descricao;

	@NotEmpty
	private String uri;

	@NotEmpty
	private String actionMethod;
	private String icone;
	private Date data = new Date();
	private Date atualizacao = new Date();
	private boolean ativo;

	@Id
	@SequenceGenerator(name = "seq_recurso", sequenceName = "seq_recurso", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_recurso")
	@Column(name = "id_recurso", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 80, nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(length = 255)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(length = 80, nullable = false)
	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Column(length = 10, nullable = false)
	public String getActionMethod() {
		return this.actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	@Column(length = 80)
	public String getIcone() {
		return this.icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	@Column(name = "cadastro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAtualizacao() {
		return this.atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	@Column(name = "ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	public boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String toString() {
		return super.toString();
	}

	public Recurso cloneFields(Recurso update) {
		setNome(update.getNome());
		setAtivo(update.getAtivo());
		setDescricao(update.getDescricao());
		setActionMethod(update.getActionMethod());
		setIcone(update.getIcone());
		setUri(update.getUri());
		return this;
	}
}