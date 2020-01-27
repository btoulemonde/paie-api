package dev.paie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Periode;
import dev.paie.repository.PeriodeRepository;

@RestController
@RequestMapping("periodes")
public class PeriodeController {

	private PeriodeRepository periodeRepository;

	/**
	 * @param periodeRepository
	 */
	public PeriodeController(PeriodeRepository periodeRepository) {
		super();
		this.periodeRepository = periodeRepository;
	}

	@GetMapping
	public List<Periode> listePeriodes() {

		return this.periodeRepository.findAll();
	}

}
