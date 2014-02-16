package br.com.squamata.gastos.vo;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSessaoVO {
	
	private ObjectId id;

	@NotEmpty(message="Preencha corretamente o campo nome!")
	private String nome;
	
	@NotEmpty(message="Preencha corretamente o campo nome de usuário!")
	private String nomeUsuario;
	
	@NotEmpty(message="Preencha corretamente o campo senha!")
	private String senha;
	
	@Bean  
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)  
	public UsuarioSessaoVO suarioSessaoVO() {  
	     return new UsuarioSessaoVO();  
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
