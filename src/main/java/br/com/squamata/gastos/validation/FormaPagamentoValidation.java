package br.com.squamata.gastos.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.squamata.gastos.domain.FormaPagamento;

public class FormaPagamentoValidation implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return FormaPagamento.class.equals(arg0);
	}

	@Override
	public void validate(Object target, Errors erros) {
		FormaPagamento formaPagamento = (FormaPagamento) target;

		if (StringUtils.isEmpty(formaPagamento.getFormaPagamento())) {
			erros.rejectValue("Descricao", "Preencha corretamente o campo descrição!");
		}
	}

}
