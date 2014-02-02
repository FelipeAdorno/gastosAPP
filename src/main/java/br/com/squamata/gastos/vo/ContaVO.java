package br.com.squamata.gastos.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

public class ContaVO {

	private ObjectId id;
	
	@NotEmpty(message="Preencha o campo forma de pagamento!")
	private String formaPagamento;
	
	@NotEmpty(message="Preencha o campo categoria!")
	private String categoria;

	@NotEmpty(message="Preencha o campo descrição!")
	private String descricao;
	
	@NotNull(message="Preencha o campo vencimento!")
	private Date vencimento;
	
	private Boolean paga;
	
	@NotNull(message="Preencha o campo parcela!")
	private Integer numeroParcela;
	
	@NotNull(message="Preencha o campo total parcelas!")
	private Integer totalParcelas;
	
	@NotNull(message="Preencha o campo valor!")
	private BigDecimal valor;
	
	public ContaVO() {
		this.paga = false;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Integer getTotalParcelas() {
		return totalParcelas;
	}

	public void setTotalParcelas(Integer totalParcelas) {
		this.totalParcelas = totalParcelas;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
