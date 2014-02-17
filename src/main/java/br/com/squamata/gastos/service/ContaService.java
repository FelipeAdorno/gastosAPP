package br.com.squamata.gastos.service;

import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.ContaVO;
import br.com.squamata.gastos.vo.TotalContaVO;

public interface ContaService extends AbstractService<ContaVO, ContaListaVO> {

	ContaListaVO buscarPorMesEAno(Integer paginaAtual, Integer quantidadeRegistros, Integer mes, Integer ano) throws UsuarioSessaoNullException; 
	
	ContaListaVO buscarContasDaSemana(Integer paginaAtual, Integer quantidadeRegistros) throws UsuarioSessaoNullException; 
	
	TotalContaVO buscarTotalMesAtual() throws UsuarioSessaoNullException;
	
	TotalContaVO buscarTotalMes(Integer mes, Integer ano) throws UsuarioSessaoNullException;

	TotalContaVO buscarTotalDividas() throws UsuarioSessaoNullException;
	
	ContaListaVO buscarContasAtrasadas(final Integer paginaAtual, final Integer quantidadeRegistros, final Integer mes, final Integer ano) throws UsuarioSessaoNullException; 
	
	TotalContaVO calcularValorTotalContasMes(final Integer mes, final Integer ano) throws UsuarioSessaoNullException; 
	
}
