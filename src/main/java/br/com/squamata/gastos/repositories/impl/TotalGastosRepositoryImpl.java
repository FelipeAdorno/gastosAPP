package br.com.squamata.gastos.repositories.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.squamata.gastos.domain.TotalConta;
import br.com.squamata.gastos.exception.UsuarioSessaoNullException;
import br.com.squamata.gastos.mapper.TotalContaMapper;
import br.com.squamata.gastos.repositories.TotalGastosRepository;
import br.com.squamata.gastos.vo.TotalContaVO;
import br.com.squamata.gastos.vo.UsuarioSessaoVO;

@Repository("totalGastosRepository")
public class TotalGastosRepositoryImpl implements TotalGastosRepository {

	private static final Logger logger = LoggerFactory
			.getLogger(TotalGastosRepositoryImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UsuarioSessaoVO usuarioSessaoVO;

	@Autowired
	private TotalContaMapper totalContaMapper;

	public TotalContaVO buscarTotalMes(final Date dataInicial,
			final Date dataFinal) throws UsuarioSessaoNullException {

		TotalContaVO totalContaVO = new TotalContaVO();

		Criteria criteria = Criteria
				.where("usuario.nome")
				.is(usuarioSessaoVO.getNome())
				.andOperator(Criteria.where("vencimento").gte(dataInicial).lt(dataFinal));

		MapReduceResults<TotalConta> results = mongoTemplate.mapReduce(
				new Query(criteria), "conta", "classpath:map.js",
				"classpath:reduce.js", TotalConta.class);

		for (TotalConta totalConta : results) { // vai vir somente um resultado
			totalContaVO = totalContaMapper
					.maperTotalContaEmTotalContaVO(totalConta);
		}

		return totalContaVO;
	}

	public TotalContaVO buscarTotal() throws UsuarioSessaoNullException {

		if (usuarioSessaoVO == null) {
			logger.error("O usuário da sessão não pode ser null");
			throw new UsuarioSessaoNullException();
		} else {

			TotalContaVO totalContaVO = new TotalContaVO();

			Criteria criteria = Criteria.where("usuario.nome").is(
					usuarioSessaoVO.getNome());

			MapReduceResults<TotalConta> results = mongoTemplate.mapReduce(
					new Query(criteria), "conta", "classpath:map.js",
					"classpath:reduce.js", TotalConta.class);

			for (TotalConta totalConta : results) { // vai vir somente um
													// resultado
				totalContaVO = totalContaMapper
						.maperTotalContaEmTotalContaVO(totalConta);
			}

			return totalContaVO;
		}
	}

}
