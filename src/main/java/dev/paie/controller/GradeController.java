package dev.paie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
