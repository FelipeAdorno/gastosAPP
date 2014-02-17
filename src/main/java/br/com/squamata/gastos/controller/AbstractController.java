package br.com.squamata.gastos.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;

import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.enumeration.TipoMensagemEnum;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.MensagemRetornoVO;
import br.com.squamata.gastos.vo.UsuarioSessaoVO;

public class AbstractController  {

	private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

	@Autowired UsuarioSessaoVO usuarioSessaoVO;
	
	@Autowired 
	private ServletContext servletContext;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Usuario getUsuarioLogado() {
		Usuario usuario = null;
		if(SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			String loginAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();
			logger.info("Usuário logado: " + loginAutenticado);
			usuario = usuarioService.buscarPorNomeUsuario(loginAutenticado);
		}
		return usuario;
	}
	
	public MensagemRetornoVO tratarErrosValidacao(List<FieldError> erros) {
		final MensagemRetornoVO retorno = new MensagemRetornoVO();
		retorno.setTipoMensagemEnum(TipoMensagemEnum.DANGER);
		for (FieldError error : erros) {
		   retorno.addMensagem(error.getDefaultMessage());
		}
		
		return retorno;
	}
	
	public String converterParamestroURL(final String entrada) {
		byte[] parametro =  null;
		try {
			parametro = entrada.toString().getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error("Erro ao converter parametros de entrada");
			e.printStackTrace();
		}
		return new String(parametro);
	}
}
