package br.com.squamata.gastos.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.squamata.gastos.domain.Role;
import br.com.squamata.gastos.domain.Usuario;
import br.com.squamata.gastos.enumeration.TipoUsuarioEnum;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.repositories.UsuarioRepository;
import br.com.squamata.gastos.service.UsuarioService;
import br.com.squamata.gastos.vo.UsuarioListaVO;

@Service(value="usuarioService")
public class UsuarioServiceProvider implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private PasswordEncoder encoder;

	@Override
	public Usuario buscarPorNomeUsuario(String nomeUsuario) {
		return usuarioRepository.findByNomeUsuario(nomeUsuario);
	}

	@Override
	public void salvar(Usuario entrada) {
		
		//Deixo o usuário habilitado
		entrada.setHabilitado(Boolean.TRUE);
		
		//Adicionando a role ao usuário
		entrada.getRoles().add(new Role(TipoUsuarioEnum.USUARIO.getRole()));

		//criptografado a senha
		entrada.setSenha(this.criptografarSenha(entrada.getSenha()));
		
		usuarioRepository.save(entrada);
		
	}
	
	private String criptografarSenha(String senha) {
		return encoder.encode(senha);
	}

	@Override
	public void atualizar(Usuario entrada) {
		usuarioRepository.save(entrada);
	}

	@Override
	public void remover(String entrada) {
//		usuarioRepository.delete(Usuario entrada);
	}

	@Override
	public Usuario buscar(String entrada, String usuario) {
//		return usuarioRepository.findOne(id);
		return null;
	}

	@Override
	public UsuarioListaVO listar(Integer paginaAtual, Integer quantidadeRegistros, String ordenacao) {
		final PageRequest pageRequest = new PageRequest(paginaAtual, quantidadeRegistros, new Sort(Sort.Direction.ASC, ordenacao));
		usuarioRepository.findAll(pageRequest);
		return null;
	}

	@Override
	public List<Usuario> listar() throws UsuarioSessaoNullException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
