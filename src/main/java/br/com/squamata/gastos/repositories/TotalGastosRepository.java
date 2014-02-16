package br.com.squamata.gastos.repositories;

import java.util.Date;

import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.vo.TotalContaVO;

public interface TotalGastosRepository {

	TotalContaVO buscarTotalMes(Date dataInicial, Date dataFinal) throws UsuarioSessaoNullException;
	
	TotalContaVO buscarTotal() throws UsuarioSessaoNullException;
	
}
