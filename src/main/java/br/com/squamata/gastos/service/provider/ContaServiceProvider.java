package br.com.squamata.gastos.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.squamata.gastos.domain.Conta;
import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.repositories.ContaRepository;
import br.com.squamata.gastos.service.ContaService;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.UsuarioSessaoVO;

@Service(value="contaService")
public class ContaServiceProvider implements ContaService {
	
	private static final Logger logger = LoggerFactory.getLogger(ContaServiceProvider.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioSessaoVO usuarioSessaoVO;
	
	@Autowired
	private ContaRepository contaRepository;

	@Override
	public void salvar(Conta entrada) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException();
		}else{
			Usuario usuario = usuarioService.buscarPorNomeUsuario(usuarioSessaoVO.getNomeUsuario());
			entrada.setUsuario(usuario);
			contaRepository.save(entrada);
		}
	}

	@Override
	public void atualizar(Conta entrada) {
		contaRepository.save(entrada);
	}

	@Override
	public void remover(Conta entrada) throws UsuarioSessaoNullException  {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			entrada = contaRepository.
					findByUsuarioNomeUsuarioAndDescricao(usuarioSessaoVO.getNomeUsuario(), entrada.getDescricao());
			logger.info(entrada.getId().getMachine()+"");
			contaRepository.delete(entrada);
		}
	}

	@Override
	public Conta buscar(Conta entrada) {
//		return categoriaRepository.findOne(id);
		return null;
	}

	@Override
	public ContaListaVO listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, ordenacao));
			Page<Conta> contas = contaRepository.findByUsuarioNomeUsuario(usuarioSessaoVO.getNomeUsuario(), pageRequest);
			return new ContaListaVO(contas.getContent(), contas.getTotalPages(), contas.getNumber());
		}
	}

	

}
