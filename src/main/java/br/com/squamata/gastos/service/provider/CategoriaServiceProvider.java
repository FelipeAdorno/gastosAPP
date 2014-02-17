package br.com.squamata.gastos.service.provider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.squamata.gastos.domain.Categoria;
import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.repositories.CategoriaRepository;
import br.com.squamata.gastos.service.CategoriaService;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.CategoriaListaVO;
import br.com.squamata.gastos.vo.UsuarioSessaoVO;

@Service(value="categoriaService")
public class CategoriaServiceProvider implements CategoriaService {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoriaServiceProvider.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioSessaoVO usuarioSessaoVO;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public void salvar(Categoria entrada) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException();
		}else{
			Usuario usuario = usuarioService.buscarPorNomeUsuario(usuarioSessaoVO.getNomeUsuario());
			entrada.setUsuario(usuario);
			categoriaRepository.save(entrada);
		}
	}

	public void atualizar(Categoria entrada) {
		categoriaRepository.save(entrada);
	}

	public void remover(String entrada) throws UsuarioSessaoNullException  {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			Categoria categoria = categoriaRepository.
					findByUsuarioNomeUsuarioAndCategoria(usuarioSessaoVO.getNomeUsuario(), entrada);
			logger.info(categoria.getId().getMachine()+"");
			categoriaRepository.delete(categoria);
		}
	}

	public Categoria buscar(String categoria, String nomeUsuario) {
		return categoriaRepository.findByUsuarioNomeUsuarioAndCategoria(nomeUsuario, categoria);
	}

	public CategoriaListaVO listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, ordenacao));
			Page<Categoria> categorias = categoriaRepository.findByUsuarioNomeUsuario(usuarioSessaoVO.getNomeUsuario(), pageRequest);
			return new CategoriaListaVO(categorias.getContent(), categorias.getTotalPages(), categorias.getNumber());
		}
	}

	public List<Categoria> listar() throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			return categoriaRepository.findByUsuarioNomeUsuario(usuarioSessaoVO.getNomeUsuario());
		}
	}

	

}
