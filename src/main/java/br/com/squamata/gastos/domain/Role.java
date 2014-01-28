package br.com.squamata.gastos.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="rule")
public class Role {

	public Role(String role) {
		this.role = role;
	}
	
	@Id
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
}
