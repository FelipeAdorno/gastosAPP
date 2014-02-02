package br.com.squamata.gastos.vo;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoriaVO {

	private ObjectId id;
	
	@NotEmpty(message="Preencha corretamente o campo categoria!")
	private String categoria;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
