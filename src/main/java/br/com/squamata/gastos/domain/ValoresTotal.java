package br.com.squamata.gastos.domain;

import java.math.BigDecimal;

public class ValoresTotal {

	private BigDecimal total;
	
	private BigDecimal totalPagao;
	
	private BigDecimal restante;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalPagao() {
		return totalPagao;
	}

	public void setTotalPagao(BigDecimal totalPagao) {
		this.totalPagao = totalPagao;
	}

	public BigDecimal getRestante() {
		return restante;
	}

	public void setRestante(BigDecimal restante) {
		this.restante = restante;
	}
}
