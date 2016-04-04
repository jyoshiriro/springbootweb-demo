package vc.com.rico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import vc.com.rico.domains.Escola;


@RepositoryRestResource(collectionResourceRel="escola", path="escola")
public interface EscolaRepository extends PagingAndSortingRepository<Escola, Long>{
	
	List<Escola> findByNome(@Param("nome") String nome);
	
	List<Escola> findByTipo(@Param("tipo") String tipo);
	
	List<Escola> findByNomeAndTipo(@Param("nome") String nome, @Param("tipo") String tipo);
	
	@Query("select e from Escola e where e.capacidade between 1 and 500")
	List<Escola> listPequenasMedias();
	
	@Query("select e from Escola e where e.capacidade between 501 and 5000")
	List<Escola> listMediasGrandes();
	
	List<Escola> findByCapacidadeBetween(@Param("i") int i, @Param("f") int f);
	
	@Modifying @Transactional
	@Query("delete from Escola where id = ?1")
	void excluir(@Param("id") Long id);
	
}
