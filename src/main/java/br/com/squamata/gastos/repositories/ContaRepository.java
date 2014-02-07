package br.com.squamata.gastos.repositories;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.squamata.gastos.domain.Conta;

@Repository
public interface ContaRepository extends PagingAndSortingRepository<Conta,ObjectId> {
	
	List<Conta> findByUsuarioNomeUsuario(String nomeUsuario);

	Page<Conta> findByUsuarioNomeUsuario(String nomeUsuario, Pageable pageable);
	
	Conta findByUsuarioNomeUsuarioAndDescricao(String nomeUsuario, String categoria);
	
	Page<Conta> findByUsuarioNomeUsuarioAndVencimentoBetween(String nomeUsuario, Date dataInicial, Date dataFinal, Pageable pageable);
	
	List<Conta> findByUsuarioNomeUsuarioAndVencimentoBetween(String nomeUsuario, Date dataInicial, Date dataFinal);
	
}
