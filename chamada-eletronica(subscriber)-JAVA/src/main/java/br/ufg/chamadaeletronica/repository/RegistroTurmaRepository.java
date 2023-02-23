package br.ufg.chamadaeletronica.repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufg.chamadaeletronica.entity.RegistroTurma;

@Repository
public interface RegistroTurmaRepository extends JpaRepository<RegistroTurma, Long>, JpaSpecificationExecutor<RegistroTurma>{

	@Query(value = "Select rt from RegistroTurma rt "
			+ "where rt.dataRegistro > :dataAtual "
			+ "and rt.discente.id = :id")
	public RegistroTurma findByDiscenteIdDate(@Param("id") Long id, @Param("dataAtual") Date dataAtual);
	
	
}
