package br.com.squamata.gastos.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.squamata.gastos.enumeration.TipoMensagemEnum;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.service.ContaService;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.ContaVO;
import br.com.squamata.gastos.vo.MensagemRetornoVO;
import br.com.squamata.gastos.vo.TotalContaVO;

@Controller
@RequestMapping(value="/contas")
public class ContasController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(ContasController.class);
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Locale locale, Model model) {
		return new ModelAndView("/restrito/contas/index");
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<MensagemRetornoVO> cadastrar(@Valid @RequestBody ContaVO conta, BindingResult result, Locale locale) {
		
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		if(result.hasErrors()) {
			tratarErrosValidacao(result.getFieldErrors());
		}else {
			logger.info("Realizando novo cadastro da conta: " + conta.getDescricao());
			try {
				contaService.salvar(conta);
				retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
				retorno.addMensagem("Cadastro realizado com sucesso!");
			} catch (UsuarioSessaoNullException e) {
				logger.error(e.getMessage());
				retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
				retorno.addMensagem("Erro ao realizar o cadastro, tente novamente!");
			}
		}
		return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar(Locale locale, Model model) {
		return new ModelAndView("/restrito/contas/listar");
	}
	
	@RequestMapping(value = "/listar/{paginaAtual}", method = RequestMethod.GET)
	public ResponseEntity<ContaListaVO> listar(@PathVariable("paginaAtual") Integer paginaAtual, Locale locale, Model model) {
		ContaListaVO contas = null;
		try {
			contas = contaService.listar(paginaAtual, 10, "descricao");
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/buscar/{paginaAtual}/{mes}/{ano}", method = RequestMethod.GET)
	public ResponseEntity<ContaListaVO> buscar(@PathVariable("paginaAtual") Integer paginaAtual, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano, Locale locale, Model model) {
		ContaListaVO contas = null;
		try {
			contas = contaService.buscarPorMesEAno(paginaAtual, 10, mes, ano);
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/buscarContasEmAtraso/{paginaAtual}/{mes}/{ano}", method = RequestMethod.GET)
	public ResponseEntity<ContaListaVO> buscarContasEmAtraso(@PathVariable("paginaAtual") Integer paginaAtual, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano, Locale locale, Model model) {
		ContaListaVO contas = null;
		try {
			contas = contaService.buscarContasAtrasadas(paginaAtual, 10, mes, ano);
			
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/calcularValorTotalContasMes/{mes}/{ano}", method = RequestMethod.GET)
	public ResponseEntity<TotalContaVO> calcularValorTotalContasMes(@PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano, Locale locale, Model model) {
		TotalContaVO total = null;
		try {
			total = contaService.calcularValorTotalContasMes(mes, ano);
			return new ResponseEntity<TotalContaVO>(total, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<TotalContaVO>(total, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/excluir/{conta}", method = RequestMethod.GET)
	public ResponseEntity<MensagemRetornoVO> excluir(@PathVariable("conta") String descricao, Locale locale) {
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		try {
			contaService.remover(descricao);
			retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
			retorno.addMensagem("Conta removida com sucesso!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
			retorno.addMensagem("Erro ao remover a conta, tente novamente!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
