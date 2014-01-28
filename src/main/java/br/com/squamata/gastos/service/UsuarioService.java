package br.com.squamata.gastos.service;

import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.vo.UsuarioListaVO;

public interface UsuarioService extends AbstractService<Usuario, UsuarioListaVO> {
	
	Usuario buscarPorNomeUsuario(final String nomeUsuario);
	
}
