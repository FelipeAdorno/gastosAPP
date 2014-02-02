package br.com.squamata.gastos.service;

import java.util.List;

import br.com.squamata.gastos.exception.UsuarioSessaoNullException;


public interface AbstractService<Domain, Lista> {

	void salvar(Domain entrada) throws UsuarioSessaoNullException;
	
	void atualizar(Domain entrada);
	
	void remover(String entrada) throws UsuarioSessaoNullException;
	
	Domain buscar(String descricao, String usuario);
	
	Lista listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) throws UsuarioSessaoNullException;
	
	List<Domain> listar() throws UsuarioSessaoNullException;
	
}
