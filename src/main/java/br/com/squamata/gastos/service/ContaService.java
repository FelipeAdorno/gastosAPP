package br.com.squamata.gastos.service;

import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.ContaVO;

public interface ContaService extends AbstractService<ContaVO, ContaListaVO> {

	ContaListaVO buscarPorMesEAno(final Integer paginaAtual, final Integer quantidadeRegistros, final Integer mes, final Integer ano) throws UsuarioSessaoNullException; 
	
}
