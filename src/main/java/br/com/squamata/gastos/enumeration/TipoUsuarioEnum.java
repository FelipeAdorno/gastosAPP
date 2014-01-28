package br.com.squamata.gastos.enumeration;

public enum TipoUsuarioEnum {

	USUARIO(1, "ROLE_USUARIO");
	
	private Integer id;
	
	private String role;
	
	private TipoUsuarioEnum(final int id, final String role) {
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
	
}
