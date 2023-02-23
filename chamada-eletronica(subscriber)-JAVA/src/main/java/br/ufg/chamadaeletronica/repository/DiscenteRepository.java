package br.ufg.chamadaeletronica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufg.chamadaeletronica.entity.Discente;

@Repository
public interface DiscenteRepository extends JpaRepository<Discente, Long>, JpaSpecificationExecutor<Discente>{

	public List<Discente> findAll();
	
	@Query(value = "Select d from Discente d where d.tagId = :tag")
	public Discente findByTagId(@Param("tag") String  tag);
	
}
