package dev.paie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Grade;
import dev.paie.repository.GradeRepository;

@RestController
@RequestMapping("grade")
public class GradeController {
	private GradeRepository gradeRepository;

	/**
	 * 
	 * @param bulletinSalaireRepository
	 */
	public GradeController(GradeRepository gradeRepository) {
		super();
		this.gradeRepository = gradeRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void salaireBase() {
		List<Grade> grades = this.gradeRepository.findAll();
		for (Grade grade : grades) {
			this.gradeRepository.salaireBase(grade.getNbHeuresBase(), grade.getTauxBase());

		}
	}

}
