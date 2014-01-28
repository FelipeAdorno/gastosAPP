package br.com.squamata.gastos.vo;

import java.util.List;

import br.com.squamata.gastos.domain.Categoria;

public class CategoriaListaVO {

	private List<Categoria> categorias;
	
	private Integer quantidadePaginas;
	
	private Integer paginaAtual;

	public CategoriaListaVO(List<Categoria> categorias, Integer quantidadePaginas, Integer paginaAtual) {
		this.setCategorias(categorias);
		this.setQuantidadePaginas(quantidadePaginas);
		this.setPaginaAtual(paginaAtual);
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
