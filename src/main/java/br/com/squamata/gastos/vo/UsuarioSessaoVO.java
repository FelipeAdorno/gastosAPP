package br.com.squamata.gastos.vo;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSessaoVO {
	
	@Bean  
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)  
	public UsuarioSessaoVO suarioSessaoVO() {  
	     return new UsuarioSessaoVO();  
	}

	private ObjectId id;
	
	private String nome;
	
	private String nomeUsuario;

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
}
