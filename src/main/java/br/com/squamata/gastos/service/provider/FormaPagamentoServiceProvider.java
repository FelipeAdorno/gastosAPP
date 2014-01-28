package br.com.squamata.gastos.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.squamata.gastos.domain.FormaPagamento;
import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.repositories.FormaPagamentoRepository;
import br.com.squamata.gastos.service.FormaPagamentoService;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.FormaPagamentoListaVO;
import br.com.squamata.gastos.vo.UsuarioSessaoVO;

@Service(value="formaPagamentoService")
public class FormaPagamentoServiceProvider implements FormaPagamentoService {
	
	private static final Logger logger = LoggerFactory.getLogger(FormaPagamentoServiceProvider.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioSessaoVO usuarioSessaoVO;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Override
	public void salvar(FormaPagamento entrada) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			Usuario usuario = usuarioService.buscarPorNomeUsuario(usuarioSessaoVO.getNomeUsuario());
			entrada.setUsuario(usuario);
			formaPagamentoRepository.save(entrada);
		}
	}

	@Override
	public void atualizar(FormaPagamento entrada) {
		formaPagamentoRepository.save(entrada);
	}

	@Override
	public void remover(FormaPagamento entrada) throws UsuarioSessaoNullException {
		logger.info(entrada.getFormaPagamento());
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			entrada = formaPagamentoRepository.
					findByUsuarioNomeUsuarioAndFormaPagamento(usuarioSessaoVO.getNomeUsuario(), entrada.getFormaPagamento());
			logger.info(entrada.getId().getMachine()+"");
			formaPagamentoRepository.delete(entrada);
		}
	}

	@Override
	public FormaPagamento buscar(FormaPagamento entrada) {
//		return formaPagamentoRepository.findOne(id);
		return null;
	}

	@Override
	public FormaPagamentoListaVO listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, ordenacao));
			Page<FormaPagamento> formasPagamento = formaPagamentoRepository.findByUsuarioNomeUsuario(usuarioSessaoVO.getNomeUsuario(), pageRequest);
			return new FormaPagamentoListaVO(formasPagamento.getContent(), formasPagamento.getTotalPages(), formasPagamento.getNumber());
		}
	}

	

}
