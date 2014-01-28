package br.com.squamata.gastos.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.squamata.gastos.domain.Conta;

@Repository
public interface ContaRepository extends PagingAndSortingRepository<Conta,ObjectId> {

	Page<Conta> findByUsuarioNomeUsuario(String nomeUsuario, Pageable pageable);
	
	Conta findByUsuarioNomeUsuarioAndDescricao(String nomeUsuario, String categoria);
	
}
