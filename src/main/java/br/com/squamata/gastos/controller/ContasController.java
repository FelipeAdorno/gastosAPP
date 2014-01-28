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

import br.com.squamata.gastos.domain.Conta;
import br.com.squamata.gastos.enumeration.TipoMensagemEnum;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.service.ContaService;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.MensagemRetornoVO;

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
	public ResponseEntity<MensagemRetornoVO> cadastrar(@Valid @RequestBody Conta conta, BindingResult result, Locale locale) {
		
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		if(result.hasErrors()) {
			tratarErrosValidacao(result.getFieldErrors());
		}else {
			logger.info("Realizando novo cadastro da forma de pagamento: " + conta.getDescricao());
			try {
				contaService.salvar(conta);
				retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
				retorno.addMensagem("Cadastro realizado com sucesso!");
			} catch (UsuarioSessaoNullException e) {
				logger.error(e.getMessage());
				retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
				retorno.addMensagem("Cadastro não realizado, tente novamente!");
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
		ContaListaVO formasPagamento = null;
		try {
			formasPagamento = contaService.listar(paginaAtual, 10, "descricao");
			return new ResponseEntity<ContaListaVO>(formasPagamento, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ContaListaVO>(formasPagamento, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/excluir/{conta}", method = RequestMethod.GET)
	public ResponseEntity<MensagemRetornoVO> excluir(@PathVariable("conta") String descricao, Locale locale) {
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		Conta conta = new Conta();
		conta.setDescricao(descricao);
		try {
			contaService.remover(conta);
			retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
			retorno.addMensagem("Remoção realizada com sucesso!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
			retorno.addMensagem("Remoção não realizada, tente novamente!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
