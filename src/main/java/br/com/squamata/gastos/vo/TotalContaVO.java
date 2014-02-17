package br.com.squamata.gastos.vo;

import java.math.BigDecimal;

public class TotalContaVO {

	private BigDecimal total;
	
	private BigDecimal totalPagao;
	
	private BigDecimal restante;
	
	public TotalContaVO() {
		this.total = BigDecimal.ZERO;
		this.totalPagao = BigDecimal.ZERO;
		this.restante = BigDecimal.ZERO;
	}
	
	public TotalContaVO(final BigDecimal total, final BigDecimal totalPago, final BigDecimal restante) {
		this.total = total;
		this.totalPagao = totalPago;
		this.restante = restante;
	}

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
