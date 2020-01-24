package dev.paie.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Grade;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.GradeRepository;

@RestController
@RequestMapping("grade")
public class GradeController {
	private BulletinSalaireRepository bulletinSalaireRepository;
	private GradeRepository gradeRepository;

	/**
	 * 
	 * @param bulletinSalaireRepository
	 */
	public GradeController(BulletinSalaireRepository bulletinSalaireRepository, GradeRepository gradeRepository) {
		super();
		this.bulletinSalaireRepository = bulletinSalaireRepository;
		this.gradeRepository = gradeRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public BigDecimal test() {
		Grade grade = new Grade();
		if (this.gradeRepository.findById(1).isPresent()) {
			grade = this.gradeRepository.findById(1).get();
		}

		BigDecimal salaireBase = this.gradeRepository.salaireBase(grade.getNbHeuresBase(), grade.getTauxBase());
		return salaireBase;
	}

}
