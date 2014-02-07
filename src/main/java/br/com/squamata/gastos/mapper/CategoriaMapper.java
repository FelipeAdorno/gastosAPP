package br.com.squamata.gastos.mapper;

import org.springframework.stereotype.Component;

import br.com.squamata.gastos.domain.Categoria;
import br.com.squamata.gastos.vo.CategoriaVO;

@Component
public class CategoriaMapper {


	public Categoria mapearCategoriaVOEmCategoria(final CategoriaVO entrada) {
		
		Categoria categoria = new Categoria();
		categoria.setCategoria(entrada.getCategoria());
		categoria.setId(entrada.getId());
		return categoria;
	}
	
	public CategoriaVO mapearCategoriaEmCCategoriaVO(final Categoria entrada) {
		CategoriaVO categoriaVO = new CategoriaVO();
		categoriaVO.setCategoria(entrada.getCategoria());
		categoriaVO.setId(entrada.getId());
		return categoriaVO;
	}
	
}
