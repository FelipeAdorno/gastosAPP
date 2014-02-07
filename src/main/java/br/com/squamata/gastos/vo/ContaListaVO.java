package br.com.squamata.gastos.vo;

import java.util.List;

import br.com.squamata.gastos.domain.Conta;

public class ContaListaVO {

	private List<Conta> contas;
	
	private Integer quantidadePaginas;
	
	private Integer paginaAtual;
	
	private TotalContaVO totalContaVO;

	public ContaListaVO(List<Conta> contas, Integer quantidadePaginas, Integer paginaAtual, TotalContaVO totalContaVO) {
		this.setContas(contas);
		this.setQuantidadePaginas(quantidadePaginas);
		this.setPaginaAtual(paginaAtual);
		this.setTotalContaVO(totalContaVO);
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
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

	public TotalContaVO getTotalContaVO() {
		return totalContaVO;
	}

	public void setTotalContaVO(TotalContaVO totalContaVO) {
		this.totalContaVO = totalContaVO;
	}
}
