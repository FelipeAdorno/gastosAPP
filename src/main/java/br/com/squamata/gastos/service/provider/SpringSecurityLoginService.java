package br.com.squamata.gastos.service.provider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.service.UsuarioService;

@Service(value = "springSecurityLoginService")
public class SpringSecurityLoginService extends
		AbstractUserDetailsAuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
	}

	@Override
	public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			logger.warn("Usuário {}: não informou a senha!", username);
			throw new BadCredentialsException("Preencha o campo senha!");
		}
		
		Usuario usuario = usuarioService.buscarPorNomeUsuario(username);
		if (usuario == null) {
			logger.warn("Usuário {} senha {}: usuário não encontrado", username, password);
			throw new UsernameNotFoundException("Nome de usuário ou senha invalido(s)!");
		}

		if (!encoder.matches(password, usuario.getSenha())) {
			logger.warn("Usuário {} senha {}: usuário não encontrado", username, password);
			throw new BadCredentialsException("Nome de usuário ou senha invalido(s)!");
		}

		// if
		// (!(UserAccountStatus.STATUS_APPROVED.name().equals(user.getStatus())))
		// {
		// logger.warn("Username {}: not approved", username);
		// throw new BadCredentialsException("User has not been approved");
		// }
		
		if (!usuario.getHabilitado()) {
			logger.warn("Usuário {}: desabilitado", username);
			throw new BadCredentialsException("Sua conta está desabilitada!");
		}

		final List<GrantedAuthority> auths;
		if (!usuario.getRoles().isEmpty()) {
			auths = AuthorityUtils.commaSeparatedStringToAuthorityList(usuario.getRolesCSV());
		} else {
			auths = AuthorityUtils.NO_AUTHORITIES;
		}

		return new User(username, password, usuario.getHabilitado(), // enabled
				true, // account not expired
				true, // credentials not expired
				true, // account not locked
				auths);
	}

}
