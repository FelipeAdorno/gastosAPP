package br.com.squamata.gastos.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.squamata.gastos.enumeration.TipoMensagemEnum;

public class MensagemRetornoVO {

	private TipoMensagemEnum tipoMensagemEnum;
	
	private List<String> mensagens = new ArrayList<String>();

	public TipoMensagemEnum getTipoMensagemEnum() {
		return tipoMensagemEnum;
	}

	public void setTipoMensagemEnum(TipoMensagemEnum tipoMensagemEnum) {
		this.tipoMensagemEnum = tipoMensagemEnum;
	}

	public List<String> getMensagens() {
		return mensagens;
	}
	
	public void addMensagem(final String mensagem) {
		this.mensagens.add(mensagem);
	}
}
