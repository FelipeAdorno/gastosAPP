package br.com.squamata.gastos.service;

import br.com.squamata.gastos.exception.UsuarioSessaoNullException;


public interface AbstractService<Domain, Lista> {

	void salvar(Domain entrada) throws UsuarioSessaoNullException;
	
	void atualizar(Domain entrada);
	
	void remover(Domain entrada) throws UsuarioSessaoNullException;
	
	Domain buscar(Domain entrada);
	
	Lista listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) throws UsuarioSessaoNullException;
	
}
