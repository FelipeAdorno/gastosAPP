package br.com.squamata.gastos.exception;

public class UsuarioSessaoNullException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsuarioSessaoNullException() {
		super("O usuário da sessão está nulo, e é necessário um usuário para inserção ou alteração de dados!");
	}
	
}
