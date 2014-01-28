package br.com.squamata.gastos.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="conta")
public class Conta {

	@Id
	private ObjectId id;
	
	private FormaPagamento formaPagamento;
	
	private Categoria categoria;
	
	private Usuario usuario;
	
	private String descricao;
	
	private Integer diaVencimento;
	
	private Integer mesVencimento;
	
	private Integer anoVencimento;
	
	private Boolean paga;
	
	private Boolean numeroParcela;
	
	private Boolean totalParcelas;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Integer getMesVencimento() {
		return mesVencimento;
	}

	public void setMesVencimento(Integer mesVencimento) {
		this.mesVencimento = mesVencimento;
	}

	public Integer getAnoVencimento() {
		return anoVencimento;
	}

	public void setAnoVencimento(Integer anoVencimento) {
		this.anoVencimento = anoVencimento;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public Boolean getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Boolean numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Boolean getTotalParcelas() {
		return totalParcelas;
	}

	public void setTotalParcelas(Boolean totalParcelas) {
		this.totalParcelas = totalParcelas;
	}


}
