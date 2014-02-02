package br.com.squamata.gastos.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.squamata.gastos.domain.Categoria;

@Repository
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria,ObjectId> {
	
	List<Categoria> findByUsuarioNomeUsuario(String nomeUsuario);

	Page<Categoria> findByUsuarioNomeUsuario(String nomeUsuario, Pageable pageable);
	
	Categoria findByUsuarioNomeUsuarioAndCategoria(String nomeUsuario, String categoria);
	
}
