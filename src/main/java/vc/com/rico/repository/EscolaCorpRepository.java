package vc.com.rico.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import vc.com.rico.domains.TipoEscolaEnum;
import vc.com.rico.domains.Escola;

@Service
public class EscolaCorpRepository {

	@PersistenceContext
    private EntityManager em;
	
	public List<Escola> listCorporativasIntegrais() {
		String sqlX = String.join("\n",
			"select e from Escola e",
			"where e.capacidade>:cap ",
			"and e.tipo=:tipo"
		);
		
		return em.createQuery(sqlX)
				.setParameter("cap", 2000)
				.setParameter("tipo", TipoEscolaEnum.INTEGRAL.getValor())
				.getResultList();
	}
}
