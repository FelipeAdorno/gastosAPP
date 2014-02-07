package br.com.squamata.gastos.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.service.ContaService;
import br.com.squamata.gastos.vo.ContaListaVO;
import br.com.squamata.gastos.vo.TotalContaVO;

@Controller
@RequestMapping(value="/gastos")
public class GastosController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(GastosController.class);
	
	private static final Integer PAGINA_ATUAL = 0;
	
	private static final Integer QUANTIDADE_REGISTROS = 8;
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Locale locale, Model model) {
		return new ModelAndView("/restrito/index");
	}
	
	@RequestMapping(value = "/buscarContasSemana", method = RequestMethod.GET)
	public ResponseEntity<ContaListaVO> buscarContasSemana(Locale locale, Model model) {
		ContaListaVO contas = null;
		try {
			contas = contaService.buscarContasDaSemana(PAGINA_ATUAL, QUANTIDADE_REGISTROS);
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ContaListaVO>(contas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/totalMes", method = RequestMethod.GET)
	public ResponseEntity<TotalContaVO> buscarTotalMes(Locale locale, Model model) {
		
		TotalContaVO totalContaVO = null;
		try {
			totalContaVO = this.contaService.buscarTotalMesAtual();
			return new ResponseEntity<TotalContaVO>(totalContaVO, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<TotalContaVO>(totalContaVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/totalDividas", method = RequestMethod.GET)
	public ResponseEntity<TotalContaVO> buscarTotalDeDividas(Locale locale, Model model) {
		TotalContaVO totalContaVO = null;
		try {
			totalContaVO = this.contaService.buscarTotalDividas();
			return new ResponseEntity<TotalContaVO>(totalContaVO, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<TotalContaVO>(totalContaVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
