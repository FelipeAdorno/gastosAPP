package br.com.squamata.gastos.service.provider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.squamata.gastos.domain.Categoria;
import br.com.squamata.gastos.domain.Conta;
import br.com.squamata.gastos.domain.FormaPagamento;
import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.mapper.ContaMapper;
import br.com.squamata.gastos.repositories.ContaRepository;
import br.com.squamata.gastos.service.ContaService;
import br.com.squamata.gastos.service.FormaPagamentoService;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.ContaVO;
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
	
	@Autowired
	private ContaMapper contaMapper;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private CategoriaServiceProvider categoriaServiceProvider;

	@Override
	public void salvar(ContaVO entrada) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException();
		}else{
			Conta conta = contaMapper.mapearContaVOEmConta(entrada);
			conta.setFormaPagamento(this.buscarFormaPagamento(entrada.getFormaPagamento()));
			conta.setCategoria(this.buscarCategoria(entrada.getCategoria()));
			
			Usuario usuario = usuarioService.buscarPorNomeUsuario(usuarioSessaoVO.getNomeUsuario());
			conta.setUsuario(usuario);
			
			if(conta.getTotalParcelas() > 1) {
				salvarContaParcelada(conta);
			}else{
				contaRepository.save(conta);
			}
		}
	}
	
	private void salvarContaParcelada(final Conta conta) {
		contaRepository.save(conta);
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(conta.getVencimento());  
		
		for (int i = conta.getNumeroParcela()+1; i <= conta.getTotalParcelas(); i++) {
			
			Conta novaConta = new Conta();
			
			novaConta.setCategoria(conta.getCategoria());
			novaConta.setDescricao(conta.getDescricao());
			novaConta.setFormaPagamento(conta.getFormaPagamento());
			novaConta.setNumeroParcela(conta.getNumeroParcela());
			novaConta.setPaga(Boolean.FALSE);
			novaConta.setTotalParcelas(conta.getTotalParcelas());
			novaConta.setNumeroParcela(i);
			novaConta.setUsuario(conta.getUsuario());
			
			calendario.add(Calendar.MONTH, 1);  
			novaConta.setVencimento(calendario.getTime());
			
			contaRepository.save(novaConta);
		
		}
	}
	
	private FormaPagamento buscarFormaPagamento(final String descricao) {
		return formaPagamentoService.buscar(descricao, usuarioSessaoVO.getNomeUsuario());
	}
	
	private Categoria buscarCategoria(final String descricao) {
		return categoriaServiceProvider.buscar(descricao, usuarioSessaoVO.getNomeUsuario());
	}

	@Override
	public void atualizar(ContaVO entrada) {
		contaRepository.save(contaMapper.mapearContaVOEmConta(entrada));
	}

	@Override
	public void remover(String entrada) throws UsuarioSessaoNullException  {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			Conta conta = contaRepository.
					findByUsuarioNomeUsuarioAndDescricao(usuarioSessaoVO.getNomeUsuario(), entrada);
			logger.info(conta.getId().getMachine()+"");
			contaRepository.delete(conta);
		}
	}

	@Override
	public ContaVO buscar(String entrada, String usuario) {
//		return categoriaRepository.findOne(id);
		return null;
	}
	
	public ContaListaVO buscarPorMesEAno(final Integer paginaAtual, final Integer quantidadeRegistros, final Integer mes, final Integer ano) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			
			/**
			 * Tenho que buscar o ultimo dia do mês anterior e o primeiro dia 
			 * do próximos mês, pq a busca é feito com menor que e maior que
			 */
			
			Integer mesAnterior = mes - 1;
			Integer proximoMes = mes + 1;
			
			//Inicio o calendario com o ano atual
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, ano);
			
			//seto o mes anterior e faço a busca do ultimo dia
			calendar.set(Calendar.MONTH, mesAnterior);
			Integer ultimoDiaMesAnterior = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //ULTIMO DIA DO MES ANTERIORO
			calendar.set(Calendar.DAY_OF_MONTH, ultimoDiaMesAnterior);
			Date dataInicial = new Date(calendar.getTimeInMillis());
			
			//seto o proximo mes e faço a busca do ultimo dia
			calendar.set(Calendar.MONTH, proximoMes);
			Integer primeiroDiaProximoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //PRIMEIRO DIA DO PRÓXIMO MES
			calendar.set(Calendar.DAY_OF_MONTH, primeiroDiaProximoMes);
			Date dataFinal = new Date(calendar.getTimeInMillis());
			
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, "vencimento"));
			
			Page<Conta> contas = contaRepository.findByUsuarioNomeUsuarioAndVencimentoBetween(usuarioSessaoVO.getNomeUsuario(), dataInicial, dataFinal, pageRequest);
			
			return new ContaListaVO(contas.getContent(), contas.getTotalPages(), contas.getNumber());
		}
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

	@Override
	public List<ContaVO> listar() throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			List<ContaVO> contasMapeadas = new ArrayList<ContaVO>();
			List<Conta> contasNaoMapeadas = contaRepository.findByUsuarioNomeUsuario(usuarioSessaoVO.getNomeUsuario());
			if(!CollectionUtils.isEmpty(contasNaoMapeadas)) {
				for (Conta conta : contasNaoMapeadas) {
					contasMapeadas.add(contaMapper.mapearContaEmContaVO(conta));
				}
			}
			return contasMapeadas;
		}
	}

	

}
