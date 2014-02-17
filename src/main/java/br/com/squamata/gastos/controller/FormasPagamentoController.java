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

import br.com.squamata.gastos.domain.FormaPagamento;
import br.com.squamata.gastos.enumeration.TipoMensagemEnum;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.service.FormaPagamentoService;
import br.com.squamata.gastos.vo.FormaPagamentoListaVO;
import br.com.squamata.gastos.vo.MensagemRetornoVO;

@Controller
@RequestMapping(value="/formasPagamento")
public class FormasPagamentoController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(FormasPagamentoController.class);
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Locale locale, Model model) {
		return new ModelAndView("/restrito/formasPagamento/index");
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<MensagemRetornoVO> cadastrar(@Valid @RequestBody FormaPagamento formaPagamento, BindingResult result, Locale locale) {
		
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		if(result.hasErrors()) {
			tratarErrosValidacao(result.getFieldErrors());
		}else {
			logger.info("Realizando novo cadastro da forma de pagamento: " + formaPagamento.getFormaPagamento());
			try {
				formaPagamentoService.salvar(formaPagamento);
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
		return new ModelAndView("/restrito/formasPagamento/listar");
	}
	
	@RequestMapping(value = "/listarTudo", method = RequestMethod.GET)
	public ResponseEntity<FormaPagamentoListaVO> listarTudo(Locale locale, Model model) {
		FormaPagamentoListaVO formasPagamento = null;
		try {
			formasPagamento = new FormaPagamentoListaVO(formaPagamentoService.listar(), null, null);
			return new ResponseEntity<FormaPagamentoListaVO>(formasPagamento, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<FormaPagamentoListaVO>(formasPagamento, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/listar/{paginaAtual}", method = RequestMethod.GET)
	public ResponseEntity<FormaPagamentoListaVO> listar(@PathVariable("paginaAtual") Integer paginaAtual, Locale locale, Model model) {
		FormaPagamentoListaVO formasPagamento = null;
		try {
			formasPagamento = formaPagamentoService.listar(paginaAtual, 10, "formaPagamento");
			return new ResponseEntity<FormaPagamentoListaVO>(formasPagamento, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<FormaPagamentoListaVO>(formasPagamento, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/excluir/{formaPagamento}", method = RequestMethod.GET)
	public ResponseEntity<MensagemRetornoVO> excluir(@PathVariable("formaPagamento") String formaPagamento, Locale locale) {
		
		//converte a forma de pagamento
		formaPagamento = super.converterParamestroURL(formaPagamento);
				
		
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		try {
			formaPagamentoService.remover(formaPagamento);
			retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
			retorno.addMensagem("Forma pagamento removida com sucesso!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
			retorno.addMensagem("Erro ao remover a forma de pagamento, tente novamente!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
