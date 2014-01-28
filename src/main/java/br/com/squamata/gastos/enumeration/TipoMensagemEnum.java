package br.com.squamata.gastos.enumeration;

public enum TipoMensagemEnum {

	SUCCESS(1, "alert-success"),
	INFO   (2, "alert-info"),
	WARNING(3, "alert-warning"),
	DANGER (4, "alert-danger");
	
	private Integer codigo;
	
	private String classe;
	
	private TipoMensagemEnum(final Integer codigo, final String classe) {
		this.codigo = codigo;
		this.classe = classe;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getClasse() {
		return classe;
	}

}
