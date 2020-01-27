package dev.paie.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.BulletinJson;
import dev.paie.entite.BulletinSalaire;
import dev.paie.service.BulletinService;
import dev.paie.vue.BulletinSalaireVue;

@RestController
@RequestMapping("bulletins")
public class BulletinSalaireController {

	private BulletinService bulletinService;

	/**
	 * @param bulletinService
	 */
	public BulletinSalaireController(BulletinService bulletinService) {
		super();
		this.bulletinService = bulletinService;
	}

	@PostMapping
	public ResponseEntity<BulletinSalaire> CreationBulletin(@RequestBody BulletinJson bullRecu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.bulletinService.postBulletins(
				bullRecu.getPrimeExceptionelle(), bullRecu.getPeriodeId(), bullRecu.getRemunerationEmployeId()));

	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<String> reservationPresent(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erreur " + exception.getMessage());
	}

	@GetMapping
	public List<BulletinSalaireVue> listerBulletins() {
		return this.bulletinService.listerBulletins();

	}
}
