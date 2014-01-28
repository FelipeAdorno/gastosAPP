package br.com.squamata.gastos.vo;

import java.util.List;

import br.com.squamata.gastos.domain.Usuario;

public class UsuarioListaVO {

	private List<Usuario> usuarios;
	
	private Integer quantidadePaginas;
	
	private Integer paginaAtual;

	public UsuarioListaVO(List<Usuario> usuarios, Integer quantidadePaginas, Integer paginaAtual) {
		this.setUsuarios(usuarios);
		this.setQuantidadePaginas(quantidadePaginas);
		this.setPaginaAtual(paginaAtual);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(Integer quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}

	public Integer getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	
}
