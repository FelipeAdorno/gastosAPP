package br.com.squamata.gastos.service.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import br.com.squamata.gastos.repositories.TotalGastosRepository;
import br.com.squamata.gastos.service.ContaService;
import br.com.squamata.gastos.service.FormaPagamentoService;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.ContaVO;
import br.com.squamata.gastos.vo.TotalContaVO;
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
	
	@Autowired
	private TotalGastosRepository totalGastosRepository;

	
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
			novaConta.setValor(conta.getValor());
			
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

	
	public void atualizar(ContaVO entrada) {
		contaRepository.save(contaMapper.mapearContaVOEmConta(entrada));
	}

	
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

	
	public ContaVO buscar(String entrada, String usuario) {
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
			Date dataInicial = this.popularDataInicial(mes, ano);
			
			Date dataFinal = this.popularDataFinal(mes, ano);
			
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, "vencimento"));
			
			Page<Conta> contas = contaRepository.findByUsuarioNomeUsuarioAndVencimentoBetween(usuarioSessaoVO.getNomeUsuario(), dataInicial, dataFinal, pageRequest);
			
			TotalContaVO totalContaVO = this.calcularValoresContaDoMes(mes, ano);
			
			//TODO: REFATORAR ISSO PARA PASSAR O VO
			return new ContaListaVO(contas.getContent(), 1, 1, totalContaVO);
		}
	}
	
	/**
	 * Busca as contas de meses anteriores que ainda não foram pagas
	 * 
	 * @return
	 */
	public ContaListaVO buscarContasAtrasadas(final Integer paginaAtual, final Integer quantidadeRegistros, final Integer mes, final Integer ano) {
		
		Date dataInicial = this.popularDataInicial(mes, ano);
		
		final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, "vencimento"));
		
		Page<Conta> contas = contaRepository.findByUsuarioNomeUsuarioAndVencimentoLessThanAndPaga(usuarioSessaoVO.getNomeUsuario(), dataInicial, Boolean.FALSE, pageRequest);
		
		TotalContaVO totalContaVO = this.calcularValoresEmAtraso(mes, ano);
		
		//TODO: REFATORAR ISSO PARA PASSAR O VO
		return new ContaListaVO(contas.getContent(), 1, 1, totalContaVO);
		
	}
	
	public TotalContaVO calcularValorTotalContasMes(final Integer mes, final Integer ano)  throws UsuarioSessaoNullException {
		
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
		
			//Apenas o total é preenchido
			TotalContaVO contasEmAtraso = this.calcularValoresEmAtraso(mes, ano);
			TotalContaVO contasDoMes = this.calcularValoresContaDoMes(mes, ano);
			
			TotalContaVO totalContaVO = new TotalContaVO();
			totalContaVO.setTotal(contasEmAtraso.getTotal().add(contasDoMes.getTotal()));
			totalContaVO.setTotalPagao(contasDoMes.getTotalPagao());
			totalContaVO.setRestante(contasDoMes.getRestante().add(contasEmAtraso.getTotal()));
		
			return totalContaVO;
		}
			
	}
	
	/**
	 * Calcula o valor total das contas em atraso
	 * 
	 * @param mes
	 * @param ano
	 * @return
	 */
	private TotalContaVO calcularValoresEmAtraso(final Integer mes, final Integer ano) {
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal valorPago = BigDecimal.ZERO;
		BigDecimal restante = BigDecimal.ZERO;
		
		Date dataInicial = this.popularDataInicial(mes, ano);
		
		List<Conta> contasNaoMapeadas =  contaRepository.findByUsuarioNomeUsuarioAndVencimentoLessThanAndPaga(
				usuarioSessaoVO.getNomeUsuario(), dataInicial, Boolean.FALSE);
		
		
		if(!CollectionUtils.isEmpty(contasNaoMapeadas)) {
			for (Conta conta : contasNaoMapeadas) {
				total = total.add(conta.getValor());
				
			}
		}
		
		//TODO: REFATORAR ISSO PARA PASSAR O VO
		return new TotalContaVO(total, valorPago, restante);
		
	}
	
	/**
	 * Calcula o valor total das contas do mês
	 * @param mes
	 * @param ano
	 * @return
	 */
	private TotalContaVO calcularValoresContaDoMes(final Integer mes, final Integer ano) {
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal valorPago = BigDecimal.ZERO;
		BigDecimal restante = BigDecimal.ZERO;
		
		Date dataInicial = this.popularDataInicial(mes, ano);
		Date dataFinal = this.popularDataFinal(mes, ano);
		
		List<Conta> contasNaoMapeadas =  contaRepository.findByUsuarioNomeUsuarioAndVencimentoBetween(
				usuarioSessaoVO.getNomeUsuario(), dataInicial, dataFinal);
		
		
		if(!CollectionUtils.isEmpty(contasNaoMapeadas)) {
			for (Conta conta : contasNaoMapeadas) {
				if(conta.getPaga()){
					valorPago = valorPago.add(conta.getValor());
				}else{
					restante = restante.add(conta.getValor());
				}
				
				total = total.add(conta.getValor());
				
			}
		}
		
		//TODO: REFATORAR ISSO PARA PASSAR O VO
		return new TotalContaVO(total, valorPago, restante);
		
	}

	
	public ContaListaVO listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, ordenacao));
			Page<Conta> contas = contaRepository.findByUsuarioNomeUsuario(usuarioSessaoVO.getNomeUsuario(), pageRequest);
			return new ContaListaVO(contas.getContent(), contas.getTotalPages(), contas.getNumber(), null);
		}
	}

	
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

	
	public ContaListaVO buscarContasDaSemana(final Integer paginaAtual, final Integer quantidadeRegistros) throws UsuarioSessaoNullException {
		if(usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException(); 
		}else{
			
			Date dataInicial = new Date();
			Date dataFinal = new Date();
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(new Date());
			
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(Calendar.AM_PM, Calendar.AM);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			dataInicial = calendar.getTime();
			
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			calendar.set(Calendar.AM_PM, Calendar.PM);
			calendar.set(Calendar.HOUR, 11);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY + 6);  
			dataFinal = calendar.getTime();
			
			final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, "vencimento"));
			
			Page<Conta> contas = contaRepository.findByUsuarioNomeUsuarioAndVencimentoBetween(usuarioSessaoVO.getNomeUsuario(), dataInicial, dataFinal, pageRequest);
			
			TotalContaVO totalContaVO = totalGastosRepository.buscarTotalMes(dataInicial, dataFinal);
		
			return new ContaListaVO(contas.getContent(), contas.getTotalPages(), contas.getNumber(), totalContaVO);
		}

	}

	
	public TotalContaVO buscarTotalMesAtual() throws UsuarioSessaoNullException {
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal valorPago = BigDecimal.ZERO;
		BigDecimal restante = BigDecimal.ZERO;

//		TotalContaVO totalContaVO = new TotalContaVO();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Date dataInicial = this.popularDataInicial(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
		Date dataFinal = this.popularDataFinal(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
		
//		totalContaVO = totalGastosRepository.buscarTotalMes(dataInicial, dataFinal);
//		
//		return totalContaVO;
		
//		List<ContaVO> contasMapeadas = new ArrayList<ContaVO>();
		List<Conta> contasNaoMapeadas =  contaRepository.findByUsuarioNomeUsuarioAndVencimentoBetween(
				usuarioSessaoVO.getNomeUsuario(), dataInicial, dataFinal);
		
		
		if(!CollectionUtils.isEmpty(contasNaoMapeadas)) {
			for (Conta conta : contasNaoMapeadas) {
				if(conta.getPaga()){
					valorPago = valorPago.add(conta.getValor());
				}else{
					restante = restante.add(conta.getValor());
				}
				
				total = total.add(conta.getValor());
				
			}
		}
		
		//TODO: REFATORAR ISSO PARA PASSAR O VO
		return new TotalContaVO(total, valorPago, restante);
	}

	
	public TotalContaVO buscarTotalMes(Integer mes, Integer ano) throws UsuarioSessaoNullException {

//		TotalContaVO totalContaVO = new TotalContaVO();
		
//		Date dataInicial = this.popularDataInicial(mes, ano);
//		Date dataFinal = this.popularDataFinal(mes, ano);
		
//		totalContaVO = totalGastosRepository.buscarTotalMes(dataInicial, dataFinal);
//		
//		return totalContaVO;
		
		return this.buscarTotalMes(mes, ano);
		
	}
	
	
	public TotalContaVO buscarTotalDividas() throws UsuarioSessaoNullException {
		
//		TotalContaVO totalContaVO = new TotalContaVO();
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal valorPago = BigDecimal.ZERO;
		BigDecimal restante = BigDecimal.ZERO;
		
//		totalContaVO = totalGastosRepository.buscarTotal();
		
//		return totalContaVO;
		
		Iterable<Conta> contas = contaRepository.findAll();

		for (Iterator<Conta> iterator = contas.iterator(); iterator.hasNext();) {
			Conta conta = iterator.next();
			if(conta.getPaga()){
				valorPago = valorPago.add(conta.getValor());
			}else{
				restante = restante.add(conta.getValor());
			}
			
			total = total.add(conta.getValor());
		}
		
		//TODO: REFATORAR ISSO PARA PASSAR O VO
		return new TotalContaVO(total, valorPago, restante);
	}
	
	
	private Date popularDataInicial(final Integer mes, final Integer ano) {
		
		Integer mesAnterior = mes - 1;
		
		//Inicio o calendario com o ano atual
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, ano);
		
		//seto o mes anterior e faço a busca do ultimo dia
		calendar.set(Calendar.MONTH, mesAnterior);
		Integer ultimoDiaMesAnterior = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //ULTIMO DIA DO MES ANTERIORO
		calendar.set(Calendar.DAY_OF_MONTH, ultimoDiaMesAnterior);
		Date dataInicial = new Date(calendar.getTimeInMillis());
		
		return dataInicial;
	}
	
	private Date popularDataFinal(final Integer mes, final Integer ano) {

		//Inicio o calendario com o ano atual
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, ano);
		
		//seto o proximo mes e faço a busca do ultimo dia
		calendar.set(Calendar.MONTH, mes);
		Integer primeiroDiaProximoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //PRIMEIRO DIA DO PRÓXIMO MES
		calendar.set(Calendar.DAY_OF_MONTH, primeiroDiaProximoMes);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date dataFinal = new Date(calendar.getTimeInMillis());
		
		return dataFinal;
	}

	

}
