package br.com.squamata.gastos.mapper;

import org.springframework.stereotype.Component;

import br.com.squamata.gastos.domain.TotalConta;
import br.com.squamata.gastos.vo.TotalContaVO;

@Component
public class TotalContaMapper {

	public TotalContaVO maperTotalContaEmTotalContaVO(final TotalConta entrada) {
		final TotalContaVO totalContaVO = new TotalContaVO();
		if(entrada.getValue() != null) {
			totalContaVO.setRestante(entrada.getValue().getRestante());
			totalContaVO.setTotal(entrada.getValue().getTotal());
			totalContaVO.setTotalPagao(entrada.getValue().getTotalPagao());
		}
		
		return totalContaVO;
	}
	
}
