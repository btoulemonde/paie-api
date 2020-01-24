package dev.paie.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.paie.entite.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	@Query("select  (g.nbHeuresBase*g.tauxBase) from Grade g where g.nbHeuresBase= :nbHeureBase and g.tauxBase = :tauxBase ")
	public BigDecimal salaireBase(@Param("nbHeureBase") BigDecimal nbHeureBase, @Param("tauxBase") BigDecimal tauxBase);

}
