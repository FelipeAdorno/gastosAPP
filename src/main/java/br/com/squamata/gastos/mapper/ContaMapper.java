package br.com.squamata.gastos.mapper;

import org.springframework.stereotype.Component;

import br.com.squamata.gastos.domain.Conta;
import br.com.squamata.gastos.vo.ContaVO;

@Component
public class ContaMapper {

	public Conta mapearContaVOEmConta(final ContaVO entrada) {
		
		Conta conta = new Conta();
		conta.setDescricao(entrada.getDescricao());
		conta.setId(entrada.getId());
		conta.setNumeroParcela(entrada.getNumeroParcela());
		conta.setPaga(entrada.getPaga());
		conta.setTotalParcelas(entrada.getTotalParcelas());
		conta.setVencimento(entrada.getVencimento());
		conta.setValor(entrada.getValor());
		return conta;
	}
	
	public ContaVO mapearContaEmContaVO(final Conta entrada) {
		ContaVO conta = new ContaVO();
		conta.setDescricao(entrada.getDescricao());
		conta.setId(entrada.getId());
		conta.setNumeroParcela(entrada.getNumeroParcela());
		conta.setPaga(entrada.getPaga());
		conta.setTotalParcelas(entrada.getTotalParcelas());
		conta.setVencimento(entrada.getVencimento());
		
		if(entrada.getFormaPagamento() != null) {
			conta.setFormaPagamento(entrada.getFormaPagamento().getFormaPagamento());
		}
		
		if(entrada.getCategoria() != null) {
			conta.setCategoria(entrada.getCategoria().getCategoria());
		}
		
		conta.setValor(entrada.getValor());
		
		return conta;
	}
	
}
