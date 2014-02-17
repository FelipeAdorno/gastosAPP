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

import br.com.squamata.gastos.domain.Categoria;
import br.com.squamata.gastos.enumeration.TipoMensagemEnum;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.service.CategoriaService;
import br.com.squamata.gastos.vo.CategoriaListaVO;
import br.com.squamata.gastos.vo.MensagemRetornoVO;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriasController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Locale locale, Model model) {
		return new ModelAndView("/restrito/categorias/index");
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public ResponseEntity<MensagemRetornoVO> cadastrar(@Valid @RequestBody Categoria categoria, BindingResult result, Locale locale) {
		
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		if(result.hasErrors()) {
			tratarErrosValidacao(result.getFieldErrors());
		}else {
			logger.info("Realizando novo cadastro da categoria: " + categoria.getCategoria());
			try {
				categoriaService.salvar(categoria);
				retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
				retorno.addMensagem("Cadastro realizado com sucesso!");
			} catch (Exception e) {
				logger.error(e.getMessage());
				retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
				retorno.addMensagem("Erro ao realizar cadastro, tente novamente!");
			}
		}
		return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar(Locale locale, Model model) {
		return new ModelAndView("/restrito/categorias/listar");
	}
	
	@RequestMapping(value = "/listarTudo", method = RequestMethod.GET)
	public ResponseEntity<CategoriaListaVO> listarTudo(Locale locale, Model model) {
		CategoriaListaVO categoria = null;
		try {
			categoria = new CategoriaListaVO( categoriaService.listar(), null, null);
			return new ResponseEntity<CategoriaListaVO>(categoria, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<CategoriaListaVO>(categoria, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/listar/{paginaAtual}", method = RequestMethod.GET)
	public ResponseEntity<CategoriaListaVO> listar(@PathVariable("paginaAtual") int paginaAtual, Locale locale, Model model) {
		CategoriaListaVO categoria = null;
		try {
			categoria = categoriaService.listar(paginaAtual, 10, "categoria");
			return new ResponseEntity<CategoriaListaVO>(categoria, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<CategoriaListaVO>(categoria, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/excluir/{categoria}", method = RequestMethod.GET)
	public ResponseEntity<MensagemRetornoVO> excluir(@PathVariable("categoria") String descricao, Locale locale) {
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		try {
			categoriaService.remover(descricao);
			retorno.setTipoMensagemEnum(TipoMensagemEnum.SUCCESS);
			retorno.addMensagem("Categoria removida com sucesso!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.OK);
		} catch (UsuarioSessaoNullException e) {
			logger.error(e.getMessage());
			retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
			retorno.addMensagem("Erro ao remover a categoria, tente novamente!");
			return new ResponseEntity<MensagemRetornoVO>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
