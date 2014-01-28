package br.com.squamata.gastos.service.provider;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.UsuarioSessaoVO;

@Service(value="springSecurityAfterLoginService")
public class SpringSecurityAfterLoginService extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private UsuarioSessaoVO usuarioSessaoVO;
	
	@Autowired
	private UsuarioService usuarioService;
	
	 @Override
     public void onAuthenticationSuccess(final HttpServletRequest request,  final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
        
         try {
        	 
        	 String url = "";
        	 
        	 Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        	 
        	 Usuario usuarioLogado = usuarioService.buscarPorNomeUsuario(authentication.getName());
        	 
        	 montarUsuarioSessao(usuarioLogado);
        	 
        	 request.getSession().setAttribute("usuarioSessaoVO", usuarioSessaoVO);
        	 
             if (roles.contains("ROLE_USUARIO")) {
            	url = "/squamataGastos/gastos/";
             }else {
            	url = "/";
             }
             
             response.sendRedirect(url);
             
         } catch (Exception e) {
             logger.error("Error in getting User()", e);
         } 
     }
	 
	public void montarUsuarioSessao(final Usuario usuario) {
		if (usuario != null) {
			usuarioSessaoVO.setId(usuario.getId());
			usuarioSessaoVO.setNome(usuario.getNome());
			usuarioSessaoVO.setNomeUsuario(usuario.getNomeUsuario());
		}
	}
	
}
