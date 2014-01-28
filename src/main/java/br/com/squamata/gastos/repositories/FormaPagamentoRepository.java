package br.com.squamata.gastos.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.squamata.gastos.domain.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends PagingAndSortingRepository<FormaPagamento,ObjectId> {

	Page<FormaPagamento> findByUsuarioNomeUsuario(String nomeUsuario, Pageable pageable);
	
	FormaPagamento findByUsuarioNomeUsuarioAndFormaPagamento(String nomeUsuario, String formaPagamento);
	
}
