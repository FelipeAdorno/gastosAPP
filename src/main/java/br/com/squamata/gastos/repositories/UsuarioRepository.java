package br.com.squamata.gastos.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.squamata.gastos.domain.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario,ObjectId> {

	Usuario findByNomeUsuario(String nomeUsuario);
	
}
