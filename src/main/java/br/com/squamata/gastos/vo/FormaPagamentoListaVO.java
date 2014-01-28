package br.com.squamata.gastos.vo;

import java.util.List;

import br.com.squamata.gastos.domain.FormaPagamento;

public class FormaPagamentoListaVO {

	private List<FormaPagamento> formasPagamento;
	
	private Integer quantidadePaginas;
	
	private Integer paginaAtual;

	public FormaPagamentoListaVO(List<FormaPagamento> formasPagamento, Integer quantidadePaginas, Integer paginaAtual) {
		this.setFormasPagamento(formasPagamento);
		this.setQuantidadePaginas(quantidadePaginas);
		this.setPaginaAtual(paginaAtual);
	}

	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
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
