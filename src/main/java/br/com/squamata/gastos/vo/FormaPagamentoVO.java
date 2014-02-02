package br.com.squamata.gastos.vo;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

public class FormaPagamentoVO {

	private ObjectId id;
	
	@NotEmpty(message="Preencha corretamente o campo descrição!")
	private String formaPagamento;
	
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
}
